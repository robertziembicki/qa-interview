#This script is to register selenium node for chrome browser from command line.
#Please ensure that the script selenium-server-hub-start.sh was executed previously!!!
java -jar ../jars/selenium-server-standalone-2.46.0.jar -role node -hub http://192.168.1.1:4444/grid/register -browser "browserName=chrome,version=43.0.2357.134,maxInstances=3,platform=MAC" -port 5551