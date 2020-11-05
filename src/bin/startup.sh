#!/usr/bin/env bash
DIR="$(dirname "$(readlink -f "$0")")";
cd ${DIR};
source ./main.sh;
showHelp(){
    printf "\n\t-c clear logs\n\t-p show progress\n\t-t show log tail\n\t-r restart\n";
}

pid=0;
cl=0;
pr=0;
tl=0;
rs=0;
clp="n";
while getopts "h?ctr" opt; do
    case "$opt" in
    h|\?)
	showHelp
	exit 0
	;;
    c)  cl=1;
	;;
    t)  tl=1;
    ;;
    r)  rs=1;
    ;;
    esac
done;
pid=$( getPid );
if [[ ${pid} -gt  "0" ]]; then
    if [[ ${rs} -gt "0" ]]; then
        ./stop.sh &
        wait $!;
    else
            printf "%b%b%b\n" "Failed to start server. Other process with pid:" ${pid} " is running.";
            exit 0;
        fi
fi
rm -rfv ../temp/*;
if [[ ${cl} -eq "1" ]]; then
	printf "\nClearing logs ...\n";
fi
main start `cat ./vmoptions`
while [[ ! -f ./application.pid ]]; do
    sleep 1;
done;
if [[ ${pr} -eq "1" ]]; then
	showProgress;
fi
if [[ ${tl} -eq "1" ]]; then
	sleep 2;
	tail -fn 500 ../logs/csd-core.log;
fi
