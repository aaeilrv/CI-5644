"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState, useEffect } from "react";
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import getJwt from "../../helpers/getJwtClient";
import getCardName from "./getCardName";

export default function CreateExchangeOffer(
  user: number,
  requestedCard: number
) {
  const API_EXCHANGE_OFFER_POST_URL =
    process.env.NEXT_PUBLIC_EXCHANGE_OFFER_URL + ``;

  const postRequest = async () => {
    const { token } = await getJwt();
    const response = await fetch(API_EXCHANGE_OFFER_POST_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        userId: user,
        requestedCardId: requestedCard,
        requeststatus: "PENDING",
        createdAt: new Date().toISOString(),
      }),
    })
      .then((response) => response.json())
      .then((data) => console.log(data))
      .catch((error) => {
        console.error("Error:", error);
      });
  };
  postRequest();
  alert("Oferta solicitado!");
}
