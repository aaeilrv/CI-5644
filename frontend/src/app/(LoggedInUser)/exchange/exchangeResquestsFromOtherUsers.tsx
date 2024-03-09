//Exchange requests from other users
//NOT OFFERS

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState, useEffect} from 'react'
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import OfferRequest from "./offerRequest";
import getJwt from "../../helpers/getJwtClient";


type exchangeProps = {
    requiredCard: number;
  };

  type barajita = {
    id: number,
    name: string,
    playerPosition: string,
    playerNumber: number,
    numberOwned: number,
    country: string,
    photoURL: string,
  }

  type exchangeRequestD = {
    id: number,
    userId: number,
    requestedCardId: number,
    requestStatus: 'PENDING' | 'ACCEPTED' | 'REJECTED',
  }

function clickMe() {
    alert("You clicked me!");
  }

  function accept() {
    alert("Intercambio exitoso!");
  }
  function reject() {
    alert("Intercambio rechazado!");
  }

  export default function ExchangeResquestFromOtherUsers({requiredCard}: exchangeProps) {
    const EMPTY_CARD_IMG_LOC = '/static/images/emptycard.png'
    const CARD_PICTURE_LOC = '/static/images/cards/'
    const { user, isLoading } = useUser();
    const [exchangeRequest, setExchangeRequest] = useState(true);
    const [exchangedContent, setExchangedContent] = useState<exchangeRequestD[]>([]);
    const [cardContent, setCardContent] = useState<barajita>();
    const [cardId, setCardId] = useState(true);
    const API_EXCHANGE_REQUEST_URL = process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL + `/hasCards/4`;

    useEffect(() => {
      const getExchangeRequestData = async () => {
        const {token} = await getJwt();
        const response = await fetch(
          API_EXCHANGE_REQUEST_URL,
          {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              "Authorization": `Bearer ${token}`
            }
          }
        );
        const data = await response.json();
        setExchangedContent(data);
        console.log(data);
      }
      getExchangeRequestData();
    }, []);

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
      console.log(data);
      setCardContent(data);
      setCardId(false);
    };
    getCardData();
  } ,[])
    //console.log(cardContent?.name)
    const cardRequested = cardContent ? cardContent.name : 'Kylian Mbappé';

    const [showPopup,setShowPopup] = useState(false)

    const handleAccept = () => {
        setShowPopup(true);
      };
    

    return(
      <div>
      {exchangedContent.length>0 ?
      exchangedContent.map((exchange, index) => ( 
        <div key={index}>
          <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
          <div className="p-4">
          <div className="flex justify-start items-center">   
          <h1 className="text-1xl font-bold space-y-4"> {`Alguien quiere intercambiar su barajita`} </h1> 
            <span>
            <Image src={CARD_PICTURE_LOC + cardRequested + ".jpeg" } alt={cardRequested} className="w-20 ml-2 mr-2" width={1080} height={1080} />
            </span>
            </div>     
          </div>
          {/** SI NO TIENE REQUEST PONER IFFFFFFF */}
        <div className="flex justify-center space-x-4">
          <Button onClick={handleAccept} text = "Ofertar"/>
          <Button onClick={clickMe} text = {`Ver album del solicitante`}/>
        </div>
        {showPopup && 
        ( <div>
            <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
            <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                <div className="p-4">
                <h1 className="text-2xl font-bold space-y-4"> Haz una oferta </h1>
                <OfferRequest />
                <div className="flex justify-center space-x-4">
                <Button onClick={() => setShowPopup(false)} text="Cerrar"/>
                <Button onClick={() => setShowPopup(false)} text="Ofertar"/>
                </div>
                </div>
                </div>
            </div>
        </div>

      )}
        </div>
        </div>
        )):
        <div>
              <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                  <div className="p-4">
                  <div className="flex justify-start items-center">   
                  <h1 className="text-1xl font-bold space-y-4"> {`No tienes solicitudes de otros usuarios`} </h1> 
                  </div>     
                  </div>
              </div>
              </div>
      }
        </div>
        
    )
  }