//Exchange requests pending for response
//(The ones the user made)

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState, useEffect } from "react";
import getJwt from "../../../helpers/getJwtClient";
import UpdateExchangeRequest from "./updateExchangeRequest";

type exchangeRequestD = {
  id: number;
  userId: number;
  requestedCardName: string;
  requestedCardId: number;
  status: string;
};

export default function UserPendingExchanges() {
  const CARD_PICTURE_LOC = "/static/images/cards/";
  const { user, isLoading } = useUser();
  const [userId, setUserId] = useState("");
  const [exchangeRequest, setExchangeRequest] = useState(true);
  const [exchangedContent, setExchangedContent] = useState<exchangeRequestD[]>([]);
  const API_EXCHANGE_REQUEST_URL = process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL + `/user/1`;

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
    <div className="py-2">
      {exchangedContent.length > 0 ? (
        exchangedContent.map((it) => (
          <div key={it.id} className="py-2">
            <div className="rounded-md bg-[#ab9ee6] px-2 py-2 text-xs font-medium text-white ring-1 ring-inset ring-blue-700/10 flex justify-between align-top">
              <div className='flex'>Solicitud: {it.requestedCardName}</div>
              <div className='text-green-700'>{it.status}</div>
              <Button
                onClick={() =>
                  UpdateExchangeRequest(
                    it.id,
                    it.userId,
                    it.requestedCardId,
                    "CANCELLED"
                  )
                }
                testId={`deleteExchangeRequestButton-${it.id}`}
                text="Cancelar"
                color="red"
              />
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
      <div>{user && user.name}</div>
    </div>
  );
}
