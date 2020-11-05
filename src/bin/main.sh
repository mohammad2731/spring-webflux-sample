#!/usr/bin/env bash
getPid(){
    pid=0;
    if [[ -f ./application.pid ]]; then
        pid=$(cat ./application.pid);
        for i in $(pgrep -f java); do
            if [[ "$i" == "$pid" ]]; then
                echo "$pid";
                return;
            fi
        done;
    fi
    pp=$(pwd);
    IFS='
';
    if [[ $(jps|grep -vi "jps") ]]; then
        for i in $(pwdx `pgrep -f java`); do
            if [[ ${pp} == $(echo ${i}|awk -F ' ' '{print $2}') ]]; then
                pid=$(echo ${i}|awk -F ':' '{print $1}');
                echo ${pid};
                return;
            fi;
        done;
    fi
    echo 0;
}
getStatus(){
    pid=$( getPid );
    if [[ "$pid"  -gt "0" ]]; then
        echo "Is running";
    else
        echo "Is not running";
    fi
}
boot(){
    pid=$( getPid );
    if [[ ${pid} -gt  "0" ]]; then
        printf "%b%b%b\n" "Failed to start server. Other process with pid:" ${pid} " is running.";
        return;
    else
        java -cp ../etc:../lib/* $@ org.springframework.boot.loader.JarLauncher
    fi
}
shutdown(){
    pid=$( getPid );
    if [[ ${pid} -gt "0" ]]; then
        kill ${pid};
        return;
    else
        printf "Process is not running.\n"
    fi;
}
error(){
    printf "Illegal arguments\nArguments must be one of below: \n\t1)start [args]*\n\t2)status\n\t3)stop\n";
}
main(){
    if [[ $# == 1 ]] || [[ $1 == "start" ]]; then
        if [[ $1 == "start" ]]; then
            boot ${@:2};
        elif [[ $1 == "status" ]]; then
            getStatus;
        elif [[ $1 == "stop" ]]; then
            shutdown;
        else
            error;
        fi;
    else
        error;
    fi;
}
