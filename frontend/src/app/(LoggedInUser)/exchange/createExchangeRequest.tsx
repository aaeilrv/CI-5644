"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState , useEffect} from 'react'
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import getJwt from "../../helpers/getJwtClient";
import getCardName from "./getCardName";



export default function CreateExchangeRequest(userID:number, requestedCardId:number) {
    const API_EXCHANGE_REQUEST_PATCH_URL = process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL + ``;

  
      const patchRequest = async () => {
        const {token} = await getJwt();
        const response = await fetch(
          API_EXCHANGE_REQUEST_PATCH_URL,
           {
          method: 'POST',
          headers: {
          'Content-Type': 'application/json',
          "Authorization": `Bearer ${token}`
          },
      body: JSON.stringify({
      user: userID,
      requestedCard: requestedCardId,
      requeststatus: "PENDING",
      createdAt: new Date().toISOString(),
      }),
})
.then(response => response.json())
.then(data => console.log(data))
.catch((error) => {
  console.error('Error:', error);
    })
  };
  patchRequest();
  alert("Intercambio solicitado!");
  }