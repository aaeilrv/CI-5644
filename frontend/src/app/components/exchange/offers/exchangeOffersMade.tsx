//Exchange offers the user made
//(Responses to another user exchange requests)

"use client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState, useEffect } from "react";
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

export default function ExchangeNotificationsMade() {
  const CARD_PICTURE_LOC = "/static/images/cards/";
  const [exchangeOffer, setExchangeOffer] = useState(true);
  const [offerContent, setOfferContent] = useState<exchangeOffer[]>([]);
  const API_EXCHANGE_REQUEST_OFFER_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_OFFER_URL + `/bidder/1`;

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
            <div>
              <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                <div className="p-4">
                  <div className="flex justify-start items-center">
                    <h1 className="text-1xl font-bold space-y-4">
                      {" "}
                      {`Ofertaste intercambiar tu barajita`}{" "}
                    </h1>
                    <div className="flex items-center">
                      <span>
                        <Image
                          src={
                            CARD_PICTURE_LOC + offer.offeredCardName + ".jpeg"
                          }
                          alt={offer.offeredCardName}
                          className="w-20 ml-2 mr-2"
                          width={1080}
                          height={1080}
                        />
                      </span>
                      <h1 className="text-1xl font-bold space-y-4">
                        {" "}
                        {` por la barajita `}{" "}
                      </h1>
                      <span>
                        <Image
                          src={
                            CARD_PICTURE_LOC + offer.requestedCardName + ".jpeg"
                          }
                          alt={offer.requestedCardName}
                          className="w-20 ml-2 mr-2"
                          width={1080}
                          height={1080}
                        />
                      </span>
                    </div>
                  </div>
                </div>
                <div className="flex justify-center space-x-4">
                  <h1 className="text-1xl font-bold space-y-4">
                    {" "}
                    {`Estatus de la oferta: ${offer.status}`}{" "}
                  </h1>
                </div>
                <div className="flex justify-center space-x-4 mt-4">
                  <Button
                    onClick={() =>
                      UpdateExchangeOffer(
                        offer.id,
                        offer.bidderId,
                        offer.exchangerequestId,
                        offer.offeredCardId,
                        "CANCELLED"
                      )
                    }
                    text="Cancelar oferta"
                    color="red"
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
                  {`No ha realizado ninguna oferta`}{" "}
                </h1>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
