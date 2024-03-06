"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Pagination from "@/app/components/pagination";
import Link from "next/link";
import localfont from '@next/font/local';
import Button from "@/app/components/Button";
import { ChevronLeftIcon, ChevronRightIcon } from "@heroicons/react/24/outline";
import ExchangePetition from "@/app/(LoggedInUser)/exchange/exchangePetition";
import ExchangeNotifications from "./exchangeOffers";
import ExchangeRequest from "./exchangeRequest";
import ExchangeResquestFromOtherUsers from "./exchangeResquestsFromOtherUsers";
import { useForm } from "react-hook-form";
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import { Fragment, useState } from 'react'
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import { number } from "zod";
import UserPendingExchanges from "./userPendingExchanges";

function clickMe() {
  alert("You clicked me!");
}

const ProtestRiot = localfont({ src: '../../../assets/fonts/ProtestRiot-Regular.ttf'});

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}
const firstItem = barajitas_temporal[0];
const secondItem = barajitas_temporal[1];

export default function Exchange() {
    const { user, isLoading } = useUser();
    if (isLoading) return <div>Loading...</div>;
    const { register, handleSubmit } = useForm();
    const [data, setData] = useState("");
    const [selected, setSelected] = useState(barajitas_temporal[0])
    const [exchanged, setExchanged] = useState(barajitas_temporal[0])
  
    return (
    <div>
      {/*
      <div>
          <ExchangePetition />
      </div>
    */}

      <div>
          <ExchangeRequest />
      </div>
      <div>
         <div className="p-4">
            <h1 className="text-2xl font-bold space-y-4"> Solicitudes de intercambio </h1>
            </div>
            <div className="p-4">
              <UserPendingExchanges requiredCard={0}/>
          </div>
      </div>
      <div>
         <div className="p-4">
            <h1 className="text-2xl font-bold space-y-4"> Ofertas de intercambio pendientes </h1>
          </div>
      <div className="p-4">
        <ExchangeNotifications requiredCard ={0}  solicitedCard = {1} />
      </div>
      <div className="p-4">
        <ExchangeNotifications requiredCard ={2}  solicitedCard = {3} />
      </div>
    </div>
    <div>
    <div className="p-4">
            <h1 className="text-2xl font-bold space-y-4"> Peticiones de otros usuarios </h1>
    </div>
      <div className="p-4">
        <ExchangeResquestFromOtherUsers requiredCard={1}/>
      </div>
    </div>
    </div>
    );
  }