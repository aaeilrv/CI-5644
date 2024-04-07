//Exchange requests from other users
//(NOT OFFERS)

"use client";
import Image from "next/image";
import { Fragment, useState, useEffect } from "react";
import OfferRequest from "../offers/offerRequest";
import getJwt from "../../../helpers/getJwtClient";

type exchangeRequestD = {
  id: number;
  userId: number;
  requestedCardId: number;
  requestedCardName: string;
  status: string;
};

export default function ExchangeRequestsFromOtherUsers() {
  const CARD_PICTURE_LOC = "/static/images/cards/";
  const [exchangedContent, setExchangedContent] = useState<exchangeRequestD[]>(
    []
  );
  const API_EXCHANGE_REQUEST_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL + `/hasCards/2`;

  useEffect(() => {
    const getExchangeRequestData = async () => {
      const { token } = await getJwt();
      const response = await fetch(API_EXCHANGE_REQUEST_URL, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      const data = await response.json();
      setExchangedContent(data);
    };
    getExchangeRequestData();
  }, []);

  return (
    <div className="py-2">
      {exchangedContent.length > 0 ? (
        exchangedContent.map((exchange, index) => (
          <div key={index} className="py-2">
            <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between align-top">
              <div className='flex'>Solicitud: {exchange.requestedCardName}</div>
              <div className='text-green-700'>{exchange.status}</div>
              <div className="flex">Creado por: {exchange.userId}</div>
              <OfferRequest />
            </div>
          </div>
        ))
      ) : (
        <div className="py-4">
          <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between">
            No existen solicitudes.
          </div>
        </div>
      )}
    </div>
  );
}
