#!/usr/bin/env bash
DIR="$(dirname "$(readlink -f "$0")")";
cd ${DIR};
source ./main.sh;
main status
