"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState , useEffect} from 'react'
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import getJwt from "../../helpers/getJwtClient";
import getCardName from "./getCardName";


export default function UpdateExchangeOffer(offer:number, user:number, request:number, offeredCard:number, statustoUpdate:string) {
    const API_EXCHANGE_REQUEST_PATCH_URL = process.env.NEXT_PUBLIC_EXCHANGE_OFFER_URL + ``;


      const patchRequest = async () => {
        const {token} = await getJwt();
        const response = await fetch(
          API_EXCHANGE_REQUEST_PATCH_URL,
           {
          method: 'PATCH',
          headers: {
          'Content-Type': 'application/json',
          "Authorization": `Bearer ${token}`
          },
      body: JSON.stringify({
      id: offer,
      bidder: user,
      exchangeRequest: request,
      offeredCard: offeredCard,
      status: statustoUpdate,
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
  if (statustoUpdate == "ACCEPTED") {
    alert("Oferta aceptada!");}
  else {
    alert("Oferta rechazada!");
  };
  }