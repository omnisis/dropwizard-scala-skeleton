#!/bin/sh

print_help() {
    echo ""
    echo "run.sh [OPTIONS] ..."
    echo "Options:"
    echo "    -f              Fastmode, uses incremental compilation and skips the shaded jar "
    echo "                    for fast compile cycles."
    echo "    -c <conf>       The configuration to use when running server (dev.yml by default)."
    echo "    -s              Skips the compile-test-package phases."
    echo ""
}

declare -a MOPTS
while getopts "h?c:sfm" opt; do
    case "$opt" in
    h|\?) 
        print_help
        exit 0
        ;;
    f) FASTMODE=1
       JAR=$(find target -name "scala-dropwizard*.jar" | grep -v "standalone")
       MOPTS+=("-P!normalmode,fastmode")
        ;;
    c) CONF=$OPTARG
        ;;
    s) RUNONLY=1
        echo "skipping build step"
        ;;
    m) MOPTS+=("-D$OPTARG")
        ;;
    esac
done
shift $((OPTIND-1))

CONF=${CONF:=confs/dev.yml}
JAR=${JAR:=$(find target -name "scala-dropwizard*-standalone.jar")}

if [ -z "$RUNONLY" ]; then
  mvn ${MOPTS[*]} clean package
fi

shift $((OPTIND-1))
java -jar target/scala-dropwizard-example-1.0-SNAPSHOT.jar server $CONF 
