"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Pagination from "@/app/components/pagination";
import Link from "next/link";
import localfont from '@next/font/local';
import Button from "@/app/components/Button";
import { ChevronLeftIcon, ChevronRightIcon } from "@heroicons/react/24/outline";

import { useForm } from "react-hook-form";

import { Fragment, useState } from 'react'
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'

function clickMe() {
  alert("Peticion de intercambio exitosa!");
}

const ProtestRiot = localfont({ src: '../../../assets/fonts/ProtestRiot-Regular.ttf'});

  const barajitas_temporal = [

    {
      id: 0,
      name: "Emiliano Martínez",
      player_number: 1,
      country: "Argentina",
      player_position: "Portero",
      photo: "https://i.pinimg.com/564x/2d/88/3b/2d883bddea69c947124837f48855a517.jpg"
    },
    {
      id: 1,
      name: "Lionel Messi",
      player_number: 10,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/736x/f3/5b/fd/f35bfdfea8114351f2a262edc25561f3.jpg"
    },
    {
      id: 2,
      name: "Ángel Di María",
      player_number: 11,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/65/b4/8f/65b48f81da0b045856c1cf3bb71b24d9.jpg"
    },
    {
      id: 3,
      name: "Lautaro Martínez",
      player_number: 22,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/4c/66/cd/4c66cd446bb0776d0c77a4c9e1cfc226.jpg"
    },
    {
      id: 4,
      name: "Guido Rodríguez",
      player_number: 22,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/29/5f/58/295f58a9f811cabfe2f8370eb9e02be6.jpg"
    },
    {
      id: 5,
      name: "Nicolás Otamendi",
      player_number: 22,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/7f/1c/09/7f1c0979e6965b7a1fbc3f96a1a7006f.jpg"
    },
    {
      id: 6,
      name: "Nahuel Molina",
      player_number: 22,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/f5/dc/f2/f5dcf27a4d99b060d3f6219f1cdeeef2.jpg"
    },
    {
      id: 7,
      name: "Joaquín Correa",
      player_number: 22,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/66/c4/f8/66c4f8198ded2770cfa1f9a8b960bb30.jpg"
    },
    {
      id: 8,
      name: "Marcos Acuña",
      player_number: 22,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/9f/2a/60/9f2a604f43afa032beb18f045da0d5c4.jpg"
    },
    {
      id: 9,
      name: "Cristian Romero",
      player_number: 22,
      country: "Argentina",
      player_position: "Delantero",
      photo: "https://i.pinimg.com/564x/4b/9b/14/4b9b1469f4246f41384258c827d1d445.jpg"
    },
  ]

function classNames(...classes: string[]) {
  return classes.filter(Boolean).join(' ')
}

export default function ExchangePetition() {
    const { user, isLoading } = useUser();
    if (isLoading) return <div>Loading...</div>;
    const { register, handleSubmit } = useForm();
    const [data, setData] = useState("");
    const [selected, setSelected] = useState(barajitas_temporal[0])
    const [exchanged, setExchanged] = useState(barajitas_temporal[0])
  
    return (
    <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
        <div className="p-4">
            <h1 className="text-2xl font-bold space-y-4"> ¡Intercambia tus Barajitas! </h1>
            <div>
                
                <Listbox value={selected} onChange={setSelected}>
                {({ open }) => (
                <>
                <Listbox.Label className="block text-sm font-medium leading-6 text-gray-900"> Quiero cambiar a </Listbox.Label>
                <div className="relative mt-2">
                    <Listbox.Button className="w-full cursor-default rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    <span className="block truncate">{selected.name}</span>
                    <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
                        <ChevronUpDownIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
                    </span>
                    </Listbox.Button>

                    <Transition
                    show={open}
                    as={Fragment}
                    leave="transition ease-in duration-100"
                    leaveFrom="opacity-100"
                    leaveTo="opacity-0"
                    >
                    <Listbox.Options className="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
                        {barajitas_temporal.map((person) => (
                        <Listbox.Option
                            key={person.id}
                            className={({ active }) =>
                            classNames(
                                active ? 'bg-indigo-600 text-white' : 'text-gray-900',
                                'relative cursor-default select-none py-2 pl-3 pr-9'
                            )
                            }
                            value={person}
                        >
                            {({ selected, active }) => (
                            <>
                                <span className={classNames(selected ? 'font-semibold' : 'font-normal', 'block truncate')}>
                                {person.name} <Image src={person.photo} alt={person.name} className="w-10" width={1080} height={1080} />
                                </span>

                                {selected ? (
                                <span
                                    className={classNames(
                                    active ? 'text-white' : 'text-indigo-600',
                                    'absolute inset-y-0 right-0 flex items-center pr-4'
                                    )}
                                >
                                    <CheckIcon className="h-5 w-5" aria-hidden="true" />
                                </span>
                                ) : null}
                            </>
                            )}
                        </Listbox.Option>
                        ))}
                    </Listbox.Options>
                    </Transition>
                </div>
                </>
                )}
                </Listbox> 
            </div>
            


            <div>
                <Listbox value={exchanged} onChange={setExchanged}>
                {({ open }) => (
                <>
                <Listbox.Label className="block text-sm font-medium leading-6 text-gray-900"> por </Listbox.Label>
                <div className="relative mt-2">
                    <Listbox.Button className="relative w-full cursor-default rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:outline-none focus:ring-2 focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    <span className="block truncate">{exchanged.name}</span>
                    <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
                        <ChevronUpDownIcon className="h-5 w-5 text-gray-400" aria-hidden="true" />
                    </span>
                    </Listbox.Button>

                    <Transition
                    show={open}
                    as={Fragment}
                    leave="transition ease-in duration-100"
                    leaveFrom="opacity-100"
                    leaveTo="opacity-0"
                    >
                    <Listbox.Options className="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
                        {barajitas_temporal.map((person) => (
                        <Listbox.Option
                            key={person.id}
                            className={({ active }) =>
                            classNames(
                                active ? 'bg-indigo-600 text-white' : 'text-gray-900',
                                'relative cursor-default select-none py-2 pl-3 pr-9'
                            )
                            }
                            value={person}
                        >
                            {({ exchanged, active }) => (
                            <>
                                <span className={classNames(exchanged ? 'font-semibold' : 'font-regular', 'block truncate')}>
                                {person.name} <Image src={person.photo} alt={person.name} className="w-10" width={1080} height={1080} />
                                </span>

                                {exchanged ? (
                                <span
                                    className={classNames(
                                    active ? 'text-white' : 'text-indigo-600',
                                    'absolute inset-y-0 right-0 flex items-center pr-4'
                                    )}
                                >
                                    <CheckIcon className="h-5 w-5" aria-hidden="true" />
                                </span>
                                ) : null}
                            </>
                            )}
                        </Listbox.Option>
                        ))}
                    </Listbox.Options>
                    </Transition>
                </div>
                </>
                )}
                </Listbox> 
            </div>
          <div style={{ display: "flex" }}>           
             <Button onClick={clickMe} style = {{ margin: 'auto', display: 'block', marginTop: '20px'}} text="Solicitar intercambio" />
          </div>
        </div>
    </div>
    );
  }