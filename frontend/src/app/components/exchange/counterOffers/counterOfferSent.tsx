//Counteroffer requests pending for response
//(The ones the user made)

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import { Fragment, useState, useEffect } from "react";
import getJwt from "../../../helpers/getJwtClient";
import Button from "../../Button";

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

export default function CounterofferSent() {
  const { user, isLoading } = useUser();
  const [exchangeRequest, setExchangeRequest] = useState(true);
  const [exchangedContent, setExchangedContent] = useState<counterOffer[]>([]);
  const API_EXCHANGE_REQUEST_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_COUNTEROFFER_URL + `/creator/me`;

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
        exchangedContent.map((it, index) => (
          <div key={it.id} className="p-4">
            <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between align-top">
              <div className='flex'>Mi contraoferta: {it.counterOfferedCardName}</div>
              <div className="flex">Barajita a recibir: {it.status}</div>
              <div className='flex'>Mi oferta original: {it.counterOfferedCardName}</div>
              <div className="flex">{it.status}</div>
              <Button
                onClick={() =>
                  console.log("Cancelar oferta")
                }
                text="Cancelar"
                color="red"
              />
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
