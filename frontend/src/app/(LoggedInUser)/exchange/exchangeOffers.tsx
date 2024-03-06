"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Pagination from "@/app/components/pagination";
import Link from "next/link";
import localfont from '@next/font/local';
import Button from "@/app/components/Button";
import { ChevronLeftIcon, ChevronRightIcon } from "@heroicons/react/24/outline";
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import { useForm } from "react-hook-form";

import { Fragment, useState } from 'react'
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import internal from "stream";
import OfferRequest from "./offerRequest";


type exchangeProps = {
    requiredCard: number;
    solicitedCard: number;
  };

const firstItem = barajitas_temporal[0];
const secondItem = barajitas_temporal[1];

function clickMe() {
    alert("You clicked me!");
  }

  function accept() {
    alert("Intercambio exitoso!");
  }
  function reject() {
    alert("Intercambio rechazado!");
  }


  export default function ExchangeNotifications({requiredCard, solicitedCard}: exchangeProps) {
    const [showPopup,setShowPopup] = useState(false)

    const handleAccept = () => {
      setShowPopup(true);
    };

    return(
        <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
         <div className="p-4">
            <div className="flex justify-start items-center">   
            <h1 className="text-1xl font-bold space-y-4"> {`Alguien quiere intercambiar su barajita`} </h1> 
            <span>
                <Image src={barajitas_temporal[requiredCard].photo} alt={barajitas_temporal[requiredCard].name} className="w-20 ml-2 mr-2" width={1080} height={1080} />
            </span>
            <h1 className="text-1xl font-bold space-y-4"> {` por tu barajita `} </h1>
            <span>
                <Image src={barajitas_temporal[solicitedCard].photo} alt={barajitas_temporal[solicitedCard].name} className="w-20 ml-2 mr-2" width={1080} height={1080} />
            </span>
            </div>     
        </div>
        <div className="flex justify-center space-x-4">
          <Button onClick={accept} text = "Aceptar" color = "green"/> 
          <Button onClick={reject}  text = "Rechazar" color = "red"/>
          <Button onClick={handleAccept} text = "Contraoferta"/>
          <Button onClick={clickMe} text = {`Ver album del solicitante`}/>
        </div>
        {showPopup && 
        ( <div>
            <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
            <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                <div className="p-4">
                <h1 className="text-2xl font-bold space-y-4"> Haz una contraoferta </h1>
                <OfferRequest />
                <div className="flex justify-center space-x-4">
                <Button onClick={() => setShowPopup(false)} text="Cerrar"/>
                <Button onClick={() => setShowPopup(false)} text="Contraofertar"/>
                </div>
                </div>
                </div>
            </div>
        </div>

      )}
        </div>
    )
  }

function setShowPopup(arg0: boolean) {
  throw new Error("Function not implemented.");
}
