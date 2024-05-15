'use client';
import React from 'react';
import { useState } from 'react'
import ExchangeRequestsFromOtherUsers from '@/app/components/exchange/requests/exchangeRequestsFromOtherUsers';
import UserPendingExchanges from '@/app/components/exchange/requests/userPendingExchanges';
import ExchangeOffersMade from '@/app/components/exchange/offers/exchangeOffersMade';
import ExchangeOffersReceived from '@/app/components/exchange/offers/exchangeOffersReceived';
import CounterofferReceived from '@/app/components/exchange/counterOffers/counterOfferReceived';
import CounterofferSent from '@/app/components/exchange/counterOffers/counterOfferSent';
import FilterButtons from '@/app/components/exchange/filters/filterButtons';
import SecondFilter from '@/app/components/exchange/filters/secondFilterButtons';
import ExchangePetition from '@/app/components/exchange/requests/exchangeRequest';

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}

export default function Exchange() {
  const [currentFilter, setCurrentFilter] = useState(0);
  const [secondFilter, setSecondFilter] = useState(0);
  const [newReq, setNewReq] = useState(false);

  return (
    <>
      <div className="py-2">
        <div className="flex grow flex-col border-r border-[#c7d3e1] bg-[#d6dfea] rounded-lg px-6">
          <div className='flex justify-between py-4'>
            <FilterButtons setCurrentFilter={setCurrentFilter} setNewReq={setNewReq}/>
            <button
              className="flex items-center justify-center rounded-md bg-yellow-400 py-2 px-4 text-xs font-bold text-black ring-1 ring-inset ring-yellow-500"
              onClick={() => setNewReq(!newReq)}>
              Crear solicitud
            </button>
          </div>
          <div className='-mt-4'>
            { !newReq && <SecondFilter currentFilter={secondFilter} setCurrentFilter={setSecondFilter}/>}
          </div>
          <div>
            {currentFilter === 0 && secondFilter == 0 && !newReq && <UserPendingExchanges />}
            {currentFilter === 0 && secondFilter == 1 && !newReq && <ExchangeRequestsFromOtherUsers />}
            {currentFilter === 1 && secondFilter == 0 && !newReq && <ExchangeOffersMade />}
            {currentFilter === 1 && secondFilter == 1 && !newReq && <ExchangeOffersReceived />}
            {currentFilter === 2 && secondFilter == 0 && !newReq && <CounterofferSent />}
            {currentFilter === 2 && secondFilter == 1 && !newReq && <CounterofferReceived />}
            {newReq && <ExchangePetition />}
          </div>
        </div>
      </div>
    </>
  )
}
