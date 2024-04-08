//Counteroffer requests pending for response
//(The ones the user received)

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState, useEffect } from "react";
import getJwt from "../../../helpers/getJwtClient";
import UpdateCounterOffer from "./updateCounterOffer";

function clickMe() {
  alert("You clicked me!");
}

type counterOffer = {
  id: number,
  exchangeOfferId: number,
  counterofferCardId: number,
  counterofferCardName: String,
  counterofferCreatorName: String,
  exchangeOfferCreatorName: String,
  exchangeRequestId: number,
  exchangeRequestCardName: String,
  status: string
};

export default function CounterofferReceived() {
  const { user, isLoading } = useUser();
  const [exchangeRequest, setExchangeRequest] = useState(true);
  const [exchangedContent, setExchangedContent] = useState<counterOffer[]>([]);
  const API_EXCHANGE_REQUEST_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_COUNTEROFFER_URL + `/receiver/me`;

  useEffect(() => {
    const getExchangeRequestData = async () => {
      const { token } = await getJwt();
      const response = await fetch(API_EXCHANGE_REQUEST_URL, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`,
        },
      });
      const data = await response.json();
      setExchangedContent(data);
      setExchangeRequest(false);
    };
    getExchangeRequestData();
  }, []);

  if (isLoading || exchangeRequest) return <div>Loading...</div>;
  return (
    <div>
      {exchangedContent.length > 0 ? (
        exchangedContent.map((it, index) => (
          <div key={it.id} className="p-4">
            <div>
            <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between align-top">
              <div className='flex'>Contraoferta Recibida: {it.counterofferCardName}</div>
              <div className="flex">Oferta original: {it.exchangeRequestCardName}</div>
              <div className='flex'>Barajita que ofrezco: {it.exchangeOfferCreatorName}</div>
              <div className='flex'>Transacción con: {it.counterofferCreatorName}</div>
              <div className="flex">{it.status}</div>
                <div className="flex justify-center space-x-4">
                  <Button
                    onClick={() =>
                      UpdateCounterOffer(
                        it.id,
                        it.exchangeOfferId,
                        it.exchangeRequestId,
                        it.counterofferCardId,
                        "ACCEPTED"
                      )
                    }
                    text="Aceptar"
                    color="green"
                  />
                  <Button
                    onClick={() =>
                      UpdateCounterOffer(
                        it.id,
                        it.exchangeOfferId,
                        it.exchangeRequestId,
                        it.counterofferCardId,
                        "REJECTED"
                      )
                    }
                    text="Rechazar"
                    color="red"
                  />
                  <Button
                    onClick={clickMe}
                    text={`Ver album del solicitante`}
                  />
                </div>
              </div>
            </div>
          </div>
        ))
      ) : (
        <div className="py-4">
          <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between">
            No existen contraofertas.
          </div>
        </div>
      )}
    </div>
  );
}
