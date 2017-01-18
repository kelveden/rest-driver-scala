package com.kelveden.restdriverscala

import java.net.ServerSocket

object PortFinder {
  def getFreePort = {
    val socket = new ServerSocket(0) {
      setReuseAddress(true)
    }
    val port = socket.getLocalPort
    socket.close
    port
  }
}
