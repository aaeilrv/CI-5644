'use client';
import React, { ReactNode } from 'react';
import { Fragment, useState } from 'react'
import ExchangeRequestsFromOtherUsers from '@/app/components/exchange/requests/exchangeRequestsFromOtherUsers';
import UserPendingExchanges from '@/app/components/exchange/requests/userPendingExchanges';
import ExchangeOffersMade from '@/app/components/exchange/offers/exchangeOffersMade';
import ExchangeOffersReceived from '@/app/components/exchange/offers/exchangeOffersReceived';
import CounterofferReceived from '@/app/components/exchange/counterOffers/counterOfferReceived';
import CounterofferSent from '@/app/components/exchange/counterOffers/counterOfferSent';
import FilterButtons from '@/app/components/exchange/filters/filterButtons';

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}

export default function Exchange() {

  const [currentFilter, setCurrentFilter] = useState(0);
  const [secondFilter, setSecondFilter] = useState(0);

  return (
    <>
      <div className="py-2">
        <div className="flex grow flex-col overflow-y-auto border-r border-[#c7d3e1] bg-[#d6dfea] rounded-lg px-6">
          <div className='flex justify-between py-4'>
            <FilterButtons currentFilter={currentFilter} setCurrentFilter={setCurrentFilter}/>
            <button className="flex items-center justify-center rounded-md bg-yellow-400 py-2 px-4 text-xs font-bold text-black ring-1 ring-inset ring-yellow-500">
              Crear solicitud
            </button>
          </div>
          {/*<div className='space-x-2 -mt-4'>
            {
              filters[0].active && filters[0].buttons.map((it) => (
                <button key={it.id} className={`inline-flex items-center rounded-md px-2 py-1 text-xs font-medium
                ${it.active ? 'bg-gray-200' : ' bg-gray-50'}
                ring-1 ring-inset ring-gray-300`}>{it.name}</button>
              ))
            }
            {
              filters[1].active && filters[1].buttons.map((it) => (
                <button key={it.id} className={`inline-flex items-center rounded-md px-2 py-1 text-xs font-medium
                ${it.active ? 'bg-gray-200' : ' bg-gray-50'}
                ring-1 ring-inset ring-gray-300`}>{it.name}</button>
              ))
            }
            {
              filters[2].active && filters[2].buttons.map((it) => (
                <button key={it.id} className={`inline-flex items-center rounded-md px-2 py-1 text-xs font-medium
                ${it.active ? 'bg-gray-200' : ' bg-gray-50'}
                ring-1 ring-inset ring-gray-300`}>{it.name}</button>
              ))
            }
          </div>*/}
          <div>
            {currentFilter === 0 && <UserPendingExchanges />}
            {currentFilter === 0 && <ExchangeRequestsFromOtherUsers />}
            {currentFilter === 1 && <ExchangeOffersMade />}
            {currentFilter === 1 && <ExchangeOffersReceived />}
            {currentFilter === 2 && <CounterofferSent />}
            {currentFilter === 2 && <CounterofferReceived />}
          </div>
        </div>
      </div>
    </>
  )
}
