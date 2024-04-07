//Exchange offers the user received
//(Responses to the user's exchange requests)

"use client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState, useEffect } from "react";
import CounterOfferRequest from "../counterOffers/counterOfferRequest";
import getJwt from "../../../helpers/getJwtClient";
import UpdateExchangeOffer from "./updateExchangeOffer";

type exchangeOffer = {
  id: number;
  bidderId: number;
  exchangerequestId: number;
  offeredCardId: number;
  requestedCardName: string;
  offeredCardName: string;
  status: "PENDING" | "ACCEPTED" | "REJECTED";
};

export default function ExchangeOffersReceived() {
  const CARD_PICTURE_LOC = "/static/images/cards/";
  const [exchangeOffer, setExchangeOffer] = useState(true);
  const [offerContent, setOfferContent] = useState<exchangeOffer[]>([]);
  const API_EXCHANGE_REQUEST_OFFER_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_OFFER_URL + `/receiver/2`;

  useEffect(() => {
    const getExchangeOfferData = async () => {
      const { token } = await getJwt();
      const response = await fetch(API_EXCHANGE_REQUEST_OFFER_URL, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      const data = await response.json();
      setOfferContent(data);
      setExchangeOffer(false);
    };
    getExchangeOfferData();
  }, []);

  return (
    <div>
      {offerContent.length > 0 ? (
        offerContent.map((offer, index) => (
          <div key={index} className="p-4">
            <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between align-top">
              <div className="flex">Solicitud: {offer.requestedCardName}</div>
              <div className="flex">Oferta: {offer.offeredCardName}</div>
              <div className="flex">Creado por: {offer.exchangerequestId}</div>
              <div className="flex">Recibido por: {offer.bidderId}</div>
              <div className="flex">{offer.status}</div>
              <div className="flex space-x-2">
                <Button
                  onClick={() =>
                    UpdateExchangeOffer(
                      offer.id,
                      offer.bidderId,
                      offer.exchangerequestId,
                      offer.offeredCardId,
                      "ACCEPTED"
                    )
                  }
                  text="Aceptar"
                  color="green"
                />
                <Button
                    onClick={() =>
                      UpdateExchangeOffer(
                        offer.id,
                        offer.bidderId,
                        offer.exchangerequestId,
                        offer.offeredCardId,
                        "REJECTED"
                      )
                    }
                    text="Rechazar"
                    color="red"
                  />
                <CounterOfferRequest />
              </div>
            </div>
          </div>
        ))
      ) : (
        <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between align-top">
          No se ha recibido ninguna oferta.
        </div>
      )}
    </div>
  );
}
