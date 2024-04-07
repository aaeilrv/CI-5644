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

export default function ExchangeResquestFromOtherUsers() {
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
    <div>
      {exchangedContent.length > 0 ? (
        exchangedContent.map((exchange, index) => (
          <div key={index} className="p-4">
            <div>
              <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                <div className="p-4">
                  <div className="flex justify-start items-center">
                    <h1 className="text-1xl font-bold space-y-4">
                      {" "}
                      {`Alguien quiere intercambiar su barajita`}{" "}
                    </h1>
                    <span>
                      <Image
                        src={
                          CARD_PICTURE_LOC +
                          exchange.requestedCardName +
                          ".jpeg"
                        }
                        alt={exchange.requestedCardName}
                        className="w-20 ml-2 mr-2"
                        width={1080}
                        height={1080}
                      />
                    </span>
                  </div>
                  <div className="flex justify-center space-x-4">
                    <h1 className="text-1xl font-bold space-y-4">
                      {" "}
                      {`Estatus de la solicitud: ${exchange.status}`}{" "}
                    </h1>
                  </div>
                </div>
                <OfferRequest />
              </div>
            </div>
          </div>
        ))
      ) : (
        <div>
          <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
            <div className="p-4">
              <div className="flex justify-start items-center">
                <h1 className="text-1xl font-bold space-y-4">
                  {" "}
                  {`No tienes solicitudes de otros usuarios`}{" "}
                </h1>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
