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

import * as restate from "@restatedev/restate-sdk";

export const userSessionRouter = restate.keyedRouter({
  addTicket: async (
    ctx: restate.KeyedContext,
    userId: string,
    ticketId: string,
  ) => {
    return true;
  },

  expireTicket: async (
    ctx: restate.KeyedContext,
    userId: string,
    ticketId: string,
  ) => {},

  checkout: async (ctx: restate.KeyedContext, userId: string) => {
    return true;
  },
});

export const userSessionApi: restate.ServiceApi<typeof userSessionRouter> = {
  path: "UserSession",
};
