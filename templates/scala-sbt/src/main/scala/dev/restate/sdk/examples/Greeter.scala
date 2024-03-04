/*
 * Copyright (c) 2024 - Restate Software, Inc., Restate GmbH
 *
 * This file is part of the Restate examples,
 * which is released under the MIT license.
 *
 * You can find a copy of the license in the file LICENSE
 * in the root directory of this repository or package or at
 * https://github.com/restatedev/examples/
 */

package dev.restate.sdk.examples

import dev.restate.sdk.Component
import dev.restate.sdk.ObjectContext
import dev.restate.sdk.common.{CoreSerdes, StateKey}
import greeter.GreeterGrpc.GreeterImplBase
import greeter.{GreetRequest, GreetResponse}
import io.grpc.stub.StreamObserver
import dev.restate.sdk.examples.Greeter.COUNT;

object Greeter{
  private val COUNT = StateKey.of[Integer]("count", CoreSerdes.JSON_INT)
}

class Greeter extends GreeterImplBase with Component {
  override def greet(request: GreetRequest, responseObserver: StreamObserver[GreetResponse]): Unit = {
    val ctx = ObjectContext.current()

    val count = ctx.get(COUNT).orElse(Integer.valueOf(1))
    ctx.set(COUNT, Integer.valueOf(count + 1))

    responseObserver.onNext(GreetResponse.newBuilder.setMessage(s"Hello ${request.getName} for the ${count} time!").build)
    responseObserver.onCompleted()
  }
}