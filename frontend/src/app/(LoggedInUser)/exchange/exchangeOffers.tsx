//Exchange offers the user received
//Responses to the user's exchange requests

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import { Fragment, useState, useEffect } from 'react'
import OfferRequest from "./offerRequest";
import getJwt from "../../helpers/getJwtClient";


type exchangeProps = {
    requiredCard: number;
    solicitedCard: number;
  };


function clickMe() {
    alert("You clicked me!");
  }

  function accept() {
    alert("Intercambio exitoso!");
  }
  function reject() {
    alert("Intercambio rechazado!");
  }

  type User = {
    id : number,
    firstName: string,
    lastName: string,
    birthDate: string,
    username: string,
    email: string,
  }

  type barajita = {
    id: number,
    name: string,
    playerPosition: string,
    playerNumber: number,
    numberOwned: number,
    country: string,
    photoURL: string,
  }

  type exchangeOffer = {
    id: number,
    bidderId: number,
    exchangeRequesId: number,
    offeredCardId: number,
    status: 'PENDING' | 'ACCEPTED' | 'REJECTED',
  }

  type exchangeRequest = {
    id: number,
    user: string,
    requestedCard: barajita,
    requestStatus: 'PENDING' | 'ACCEPTED' | 'REJECTED',
  }


  export default function ExchangeNotifications({requiredCard, solicitedCard}: exchangeProps) {
    const EMPTY_CARD_IMG_LOC = '/static/images/emptycard.png'
    const CARD_PICTURE_LOC = '/static/images/cards/'
    const { user, isLoading } = useUser();
    const [exchangeOffer, setExchangeOffer] = useState(true);
    const [offerContent, setOfferContent] = useState<exchangeOffer[]>([]);
    const [cardContent, setCardContent] = useState<barajita>();
    const [cardId, setCardId] = useState(true);
    const API_EXCHANGE_REQUEST_OFFER_URL = process.env.NEXT_PUBLIC_EXCHANGE_OFFER_URL + `/receiver/2`;

    /*
    useEffect(() => {
      const getExchangeOfferData = async () => {
        const {token} = await getJwt();
        const response = await fetch(
          API_EXCHANGE_REQUEST_OFFER_URL,
          {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              "Authorization": `Bearer ${token}`
            }
          }
        )
        const data = await response.json();
        console.log(data);
        setOfferContent(data.content);
        setExchangeOffer(false);
      };
      getExchangeOfferData();
    },[])
*/

    const API_CARD_URL = process.env.NEXT_PUBLIC_CARD_API_URL + `/${5}`;

  useEffect(() => {
    const getCardData = async () => {
      const {token} = await getJwt();
      const response = await fetch(
        API_CARD_URL,
        {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            "Authorization": `Bearer ${token}`
          }
        }
      )
      const data = await response.json();
      //console.log(data);
      setCardContent(data);
      setCardId(false);
    };
    getCardData();
  } ,[])
    //console.log(cardContent?.name)

    if(isLoading || exchangeOffer) return <div>Loading...</div>;

    const [showPopup,setShowPopup] = useState(false)

    const handleAccept = () => {
      setShowPopup(true);
    };

    return(
        <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
         <div className="p-4">
            <div className="flex justify-start items-center">   
            <h1 className="text-1xl font-bold space-y-4"> {`Alguien quiere intercambiar su barajita`} </h1> 
             {/**Cambiar */}
              {offerContent.map((offer, index) => (
                
                <div key={index}>
                <span>
                    <Image src={CARD_PICTURE_LOC + offer.offeredCardId + '.jpeg'} alt={offer.offeredCardId} className="w-20 ml-2 mr-2" width={1080} height={1080} />
                </span>
                 <h1 className="text-1xl font-bold space-y-4"> {` por tu barajita `} </h1>
                <span>
                    <Image src={CARD_PICTURE_LOC + offer.exchangeRequesId + '.jpeg'} alt={offer.exchangeRequesId} className="w-20 ml-2 mr-2" width={1080} height={1080} />
                </span>
                
            
             {/**
            <span>
                <Image src={barajitas_temporal[requiredCard].photo} alt={barajitas_temporal[requiredCard].name} className="w-20 ml-2 mr-2" width={1080} height={1080} />
            </span>
            <h1 className="text-1xl font-bold space-y-4"> {` por tu barajita `} </h1>
            <span>
                <Image src={barajitas_temporal[solicitedCard].photo} alt={barajitas_temporal[solicitedCard].name} className="w-20 ml-2 mr-2" width={1080} height={1080} />
            </span>
             **/}
            </div>  
            ))}   
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
        </div>
    )
  }

function setShowPopup(arg0: boolean) {
  throw new Error("Function not implemented.");
}
