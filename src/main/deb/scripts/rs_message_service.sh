

[ -f /etc/default/rs_message_service ] && . /etc/default/rs_message_service

echo Start RS message service

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

   echo ""
   echo "############################"
   echo "JAVA: $JAVA $VERSION"
   echo "JVM OPTIONS: $RS_MESSAGE_SERVICE_JAVA_OPTS"
   echo "RS_MESSAGE_SERVICE ROLE: $role"
   echo "RS_MESSAGE_SERVICE OPTIONS: $RS_MESSAGE_SERVICE_OPTIONS"
   echo "RS_MESSAGE_SERVICE JAR: $RS_MESSAGE_SERVICE_JAR"
   echo "############################"
   echo ""

   echo -n "Starting Okapi..."
   exec $JAVA $OKAPI_JAVA_OPTS -jar "$OKAPI_JAR" $role $OKAPI_OPTIONS <&- &

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
