"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Pagination from "@/app/components/pagination";
import Link from "next/link";
import localfont from '@next/font/local';
import Button from "@/app/components/Button";
import { ChevronLeftIcon, ChevronRightIcon } from "@heroicons/react/24/outline";
import ExchangePetition from "./exchangeRequest";
import { useForm } from "react-hook-form";
import { Fragment, useState } from 'react'
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import internal from "stream";
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import OfferRequest from "./offerRequest";

type exchangeProps = {
    requiredCard: number;
  };

const firstItem = barajitas_temporal[0];
const secondItem = barajitas_temporal[1];

function deleteRequest() {
    alert("Request deleted!");
  }


  export default function UserPendingExchanges({requiredCard}: exchangeProps) {
    return(
        <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
         <div className="p-4">
            <div className="flex justify-start items-center">   
            <h1 className="text-1xl font-bold space-y-4"> {`Solicitas intercambiar tu barajita`} </h1> 
            <span>
                <Image src={barajitas_temporal[requiredCard].photo} alt={barajitas_temporal[requiredCard].name} className="w-20 ml-2 mr-2" width={1080} height={1080} />
            </span>
            </div>     
        </div>
        <div className="flex justify-center space-x-4">
          <Button onClick={deleteRequest} text = {"Borrar solicitud de intercambio"}/>
        </div>
        </div>
    )
  }