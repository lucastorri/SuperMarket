#!/bin/bash

EDITOR=mate

function editFiles {
  $EDITOR `find $1 -iname $2 | tr '\\n' ' '`
}
BASEPATH=`dirname $0`/../


$EDITOR $BASEPATH/src/main/java/supermarket

editFiles $BASEPATH/src/test/ 'CheckoutCounterTest*'

editFiles $BASEPATH/src/test/ 'CustomerTest*'

$EDITOR $BASEPATH/src/test/scala/other
