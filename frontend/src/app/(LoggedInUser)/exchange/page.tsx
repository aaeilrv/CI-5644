//Exchange view

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import localfont from "@next/font/local";
import ExchangeNotifications from "./exchangeOffersRecibidas";
import ExchangeNotificationsMade from "./exchangeOffersHechas";
import ExchangeRequest from "./exchangeRequest";
import ExchangeResquestFromOtherUsers from "./exchangeResquestsFromOtherUsers";
import { useForm } from "react-hook-form";
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import { Fragment, useEffect, useState } from "react";
import UserPendingExchanges from "./userPendingExchanges";
import UserCounteroffers from "./counterOfferRequests";
import getJwt from "../../helpers/getJwtClient";
import UserCounteroffersMade from "./counterOfferSended";
function clickMe() {
  alert("You clicked me!");
}

const ProtestRiot = localfont({
  src: "../../../assets/fonts/ProtestRiot-Regular.ttf",
});

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(" ");
}

export default function Exchange() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (
    <div>
      <div>
        <ExchangeRequest />
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Mis solicitudes de intercambio{" "}
          </h1>
        </div>
        <div className="p-4">
          <UserPendingExchanges/>
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Ofertas de intercambio pendientes{" "}
          </h1>
        </div>
        <div className="p-4">
          <ExchangeNotifications />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Ofertas de intercambio hechas{" "}
          </h1>
        </div>
        <div className="p-4">
          <ExchangeNotificationsMade/>
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Peticiones de otros usuarios{" "}
          </h1>
        </div>
        <div className="p-4">
          <ExchangeResquestFromOtherUsers />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Contraofertas recibidas{" "}
          </h1>
        </div>
        <div className="p-4">
          <UserCounteroffers />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Contraofertas enviadas{" "}
          </h1>
        </div>
        <div className="p-4">
          <UserCounteroffersMade />
        </div>
      </div>
    </div>
  );
}
