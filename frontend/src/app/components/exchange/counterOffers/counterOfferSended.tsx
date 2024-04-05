//Counteroffer requests pending for response
//(The ones the user made)

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import { Fragment, useState, useEffect } from "react";
import getJwt from "../../../helpers/getJwtClient";

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

export default function UserCounteroffersMade() {
  const CARD_PICTURE_LOC = "/static/images/cards/";
  const { user, isLoading } = useUser();
  const [exchangeRequest, setExchangeRequest] = useState(true);
  const [exchangedContent, setExchangedContent] = useState<counterOffer[]>([]);
  const API_EXCHANGE_REQUEST_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_COUNTEROFFER_URL + `/creator/2`;

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
      console.log(data);
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
                      {`Contraofertas tu barajita `}{" "}
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
                      {` a cambio de  `}{" "}
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
                    <h1 className="text-1xl font-bold space-y-4">
                      {" "}
                      {` en lugar de  `}{" "}
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
                  </div>
                </div>
                <div className="flex justify-center space-x-4">
                  <h1 className="text-1xl font-bold space-y-4">
                    {" "}
                    {`Estatus de la contraoferta: ${exchange.status}`}{" "}
                  </h1>
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
                  {`No ha realizado ninguna contraoferta`}{" "}
                </h1>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
