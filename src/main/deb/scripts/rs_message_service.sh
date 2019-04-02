#!/bin/bash

[ -f /etc/default/rs_message_service ] && . /etc/default/rs_message_service

echo Start RS message service

# Look for Java and check version
java_check() {
   if [ -n "$JAVA_HOME" -a -x "$JAVA_HOME/bin/java" ]; then
      JAVA="$JAVA_HOME/bin/java"
   else
      JAVA=`which java`
   fi

   if [ ! -x "$JAVA" ]; then
      echo "Could not find any executable java binary."
      echo "Install java in your PATH or set JAVA_HOME"
      exit 1
   else
      VERSION=$("$JAVA" -version 2>&1 | awk -F '"' '/version/ { print $2 }' \
              | awk -F '.' '{ print $2 }')
      if [ "$VERSION" -lt "8" ];  then
         echo "Java version 8 or higher is required."
         exit 0
      fi
   fi
}  # end java_check

java_check

if [ ! -d "${PID_DIR}" ]; then
  mkdir -p ${PID_DIR}
fi

if [ -f "${PID_DIR}/rs_message_service.pid" ]; then
   PID=`cat ${PID_DIR}/rs_message_service.pid`
   if ps -p $PID > /dev/null; then
      echo "RS Message Service already running with [${PID}]"
      exit 0
   else
      echo "Pid file exists, but the Pid does not exist."
      echo "Remove ${PID_DIR}/rs_message_service.pid and retry."
      exit 1
   fi
else
   RS_MESSAGE_SERVICE_JAR=`ls /opt/rs-message-service/lib/resource-sharing-message-services*.jar`

   echo ""
   echo "############################"
   echo "JAVA: $JAVA $VERSION"
   echo "JVM OPTIONS: $RS_MESSAGE_SERVICE_JAVA_OPTS"
   echo "RS_MESSAGE_SERVICE OPTIONS: $RS_MESSAGE_SERVICE_OPTIONS"
   echo "RS_MESSAGE_SERVICE JAR: $RS_MESSAGE_SERVICE_JAR"
   echo "############################"
   echo ""

   echo -n "Starting RS Message services..."
   exec $JAVA $RS_MESSAGE_SERVICE_JAVA_OPTS -jar "$RS_MESSAGE_SERVICE_JAR" $RS_MESSAGE_SERVICE_OPTIONS > /var/log/rs-message-service/out 2> /var/log/rs-message-service/err <&- &

   RETVAL=$?
   PID=$!
   [ $RETVAL -eq 0 ] || exit $RETVAL
   sleep 3
   if ! ps -p $PID > /dev/null; then
      exit 1
   else
      echo "$PID" > ${PID_DIR}/rs_message_service.pid
      echo "OK [$PID]"
      exit 0
   fi

fi

exit $?
