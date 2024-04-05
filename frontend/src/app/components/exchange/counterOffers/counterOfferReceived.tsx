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
  id: number;
  offeredCardId: number;
  exchangeRequestId: number;
  exchangeOfferId: number;
  status: string;
  requestCardName: string;
  offeredCardName: string;
  counterOfferedCardName: string;
};

export default function UserCounteroffers() {
  const CARD_PICTURE_LOC = "/static/images/cards/";
  const { user, isLoading } = useUser();
  const [exchangeRequest, setExchangeRequest] = useState(true);
  const [exchangedContent, setExchangedContent] = useState<counterOffer[]>([]);
  const API_EXCHANGE_REQUEST_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_COUNTEROFFER_URL + `/receiver/1`;

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
      setExchangeRequest(false);
    };
    getExchangeRequestData();
  }, []);

  if (isLoading || exchangeRequest) return <div>Loading...</div>;
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
                      {`Contraoferta de la barajita`}{" "}
                    </h1>
                    <span>
                      <Image
                        src={
                          CARD_PICTURE_LOC +
                          exchange.counterOfferedCardName +
                          ".jpeg"
                        }
                        alt={exchange.counterOfferedCardName}
                        className="w-20 ml-2 mr-2"
                        width={1080}
                        height={1080}
                      />
                    </span>
                    <h1 className="text-1xl font-bold space-y-4">
                      {" "}
                      {` a cambio de tu barajita  `}{" "}
                    </h1>
                    <span>
                      <Image
                        src={
                          CARD_PICTURE_LOC + exchange.offeredCardName + ".jpeg"
                        }
                        alt={exchange.offeredCardName}
                        className="w-20 ml-2 mr-2"
                        width={1080}
                        height={1080}
                      />
                    </span>
                    <h1 className="text-1xl font-bold space-y-4">
                      {" "}
                      {` en lugar de  `}{" "}
                    </h1>
                    <span>
                      <Image
                        src={
                          CARD_PICTURE_LOC + exchange.requestCardName + ".jpeg"
                        }
                        alt={exchange.requestCardName}
                        className="w-20 ml-2 mr-2"
                        width={1080}
                        height={1080}
                      />
                    </span>
                  </div>
                </div>
                <div className="flex justify-center space-x-4">
                  <h1 className="text-1xl font-bold space-y-4">
                    {" "}
                    {`Estatus de la contraoferta: ${exchange.status}`}{" "}
                  </h1>
                </div>
                <div className="flex justify-center space-x-4">
                  <Button
                    onClick={() =>
                      UpdateCounterOffer(
                        exchange.id,
                        exchange.exchangeOfferId,
                        exchange.exchangeRequestId,
                        exchange.offeredCardId,
                        "ACCEPTED"
                      )
                    }
                    text="Aceptar"
                    color="green"
                  />
                  <Button
                    onClick={() =>
                      UpdateCounterOffer(
                        exchange.id,
                        exchange.exchangeOfferId,
                        exchange.exchangeRequestId,
                        exchange.offeredCardId,
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
        <div>
          <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
            <div className="p-4">
              <div className="flex justify-start items-center">
                <h1 className="text-1xl font-bold space-y-4">
                  {" "}
                  {`No ha recibido ninguna contraoferta`}{" "}
                </h1>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
