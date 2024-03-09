//Exchange offers the user received
//(Responses to the user's exchange requests)

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState, useEffect } from "react";
import OfferRequest from "./offerRequest";
import getJwt from "../../helpers/getJwtClient";
import UpdateExchangeOffer from "./updateExchangeOffer";
import { off } from "process";


function clickMe() {
  alert("Oferta contraofertada!");
}

type barajita = {
  id: number;
  name: string;
  playerPosition: string;
  playerNumber: number;
  numberOwned: number;
  country: string;
  photoURL: string;
};

type exchangeOffer = {
  id: number;
  bidderId: number;
  exchangerequestId: number;
  offeredCardId: number;
  status: "PENDING" | "ACCEPTED" | "REJECTED";
};

export default function ExchangeNotifications() {
  const CARD_PICTURE_LOC = "/static/images/cards/";
  const [exchangeOffer, setExchangeOffer] = useState(true);
  const [offerContent, setOfferContent] = useState<exchangeOffer[]>([]);
  const [cardContent, setCardContent] = useState<barajita>();
  const [cardId, setCardId] = useState(true);
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
      //console.log(data);
      setOfferContent(data);
      setExchangeOffer(false);
    };
    getExchangeOfferData();
  }, []);

  const API_CARD_URL = process.env.NEXT_PUBLIC_CARD_API_URL + `/${5}`;

  useEffect(() => {
    const getCardData = async () => {
      const { token } = await getJwt();
      const response = await fetch(API_CARD_URL, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      const data = await response.json();
      //console.log(data);
      setCardContent(data);
      setCardId(false);
    };
    getCardData();
  }, []);
  //console.log(cardContent?.name)

  const cardRequested = cardContent ? cardContent.name : "Kylian Mbappé";

  //if(isLoading || exchangeOffer) return <div>Loading...</div>;

  const [showPopup, setShowPopup] = useState(false);

  const handleAccept = () => {
    setShowPopup(true);
  };

  return (
    <div>
      {offerContent.length > 0 ? (
        offerContent.map((offer, index) => (
          <div className="p-4">
          <div key={index}>
          <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
            <div className="p-4">
              <div className="flex justify-start items-center">
                <h1 className="text-1xl font-bold space-y-4">
                  {" "}
                  {`Alguien quiere intercambiar su barajita`}{" "}
                </h1>
                  <div className="flex items-center">
                    <span>
                      <Image
                        src={CARD_PICTURE_LOC + cardRequested + ".jpeg"}
                        alt={cardRequested}
                        className="w-20 ml-2 mr-2"
                        width={1080}
                        height={1080}
                      />
                    </span>
                    <h1 className="text-1xl font-bold space-y-4">
                      {" "}
                      {` por tu barajita `}{" "}
                    </h1>
                    <span>
                      <Image
                        src={CARD_PICTURE_LOC + cardRequested + ".jpeg"}
                        alt={cardRequested}
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
                <Button onClick={handleAccept} text="Contraoferta" />
                <Button onClick={clickMe} text={`Ver album del solicitante`} />
              </div>
              {showPopup && (
                <div>
                  <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
                    <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                      <div className="p-4">
                        <h1 className="text-2xl font-bold space-y-4">
                          {" "}
                          Haz una contraoferta{" "}
                        </h1>
                        <OfferRequest />
                        <div className="flex justify-center space-x-4">
                          <Button
                            onClick={() => setShowPopup(false)}
                            text="Cerrar"
                          />
                          <Button
                            onClick={() => setShowPopup(false)}
                            text="Contraofertar"
                          />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              )}
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
