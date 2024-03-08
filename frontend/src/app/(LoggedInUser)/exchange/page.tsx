"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import localfont from '@next/font/local';
import ExchangeNotifications from "./exchangeOffers";
import ExchangeRequest from "./exchangeRequest";
import ExchangeResquestFromOtherUsers from "./exchangeResquestsFromOtherUsers";
import { useForm } from "react-hook-form";
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import { Fragment, useEffect, useState } from 'react'
import UserPendingExchanges from "./userPendingExchanges";
import getJwt from "../../helpers/getJwtClient";
function clickMe() {
  alert("You clicked me!");
}

const ProtestRiot = localfont({ src: '../../../assets/fonts/ProtestRiot-Regular.ttf'});

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}

export default function Exchange() {
    const { user, isLoading } = useUser();
    if (isLoading) return <div>Loading...</div>;
    const { register, handleSubmit } = useForm();
    const [exchangeRequest, setExchangeRequest] = useState(true);
    const [selected, setSelected] = useState(barajitas_temporal[0])
    const [exchanged, setExchanged] = useState(barajitas_temporal[0])
    //const API_EXCHANGE_REQUEST_URL = process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL + `/exchangeRequest`;
    //const API_EXCHANGE_OFFER_URL = process.env.NEXT_PUBLIC_EXCHANGE_OFFER_URL + `/exchangeOffer`;
    //const API_EXCHANGE_COUNTEROFFER_URL = process.env.NEXT_PUBLIC_EXCHANGE_COUNTEROFFER_URL + `/exchangeCounterOffer`;
  

    return (
    <div>
      <div>
          <ExchangeRequest />
      </div>
      <div>
         <div className="p-4">
            <h1 className="text-2xl font-bold space-y-4">  Mis solicitudes de intercambio </h1>
            </div>
            <div className="p-4">
              <UserPendingExchanges requiredCard={0}/>
          </div>
      </div>
      <div>
         <div className="p-4">
            <h1 className="text-2xl font-bold space-y-4"> Ofertas de intercambio pendientes </h1>
          </div>
      <div className="p-4">
        <ExchangeNotifications requiredCard ={0}  solicitedCard = {1} />
      </div>
      <div className="p-4">
        <ExchangeNotifications requiredCard ={2}  solicitedCard = {3} />
        {/** CONTRAOFERTAS QUE NOSOTROS HICIMOS???? 
         *   CONTRAOFERTAS QUE OTROS NOS HICIERON????
         *  OFERTAS QUE NOSOTROS HICIMOS???
        */}
      </div>
    </div>
    <div>
    <div className="p-4">
            <h1 className="text-2xl font-bold space-y-4"> Peticiones de otros usuarios </h1>
    </div>
      <div className="p-4">
        <ExchangeResquestFromOtherUsers requiredCard={1}/>
      </div>
    </div>
    </div>
    );
  }