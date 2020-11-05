#!/usr/bin/env bash
DIR="$(dirname "$(readlink -f "$0")")";
cd ${DIR};
source ./main.sh;
main stop;
while [ "$( getPid )" -gt "0" ]; do
        printf ". ";
        sleep 1;
done;
printf "\nStop complete\n";
