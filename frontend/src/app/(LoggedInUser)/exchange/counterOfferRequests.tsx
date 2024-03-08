//Counteroffer requests pending for response
//The ones the user received

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState , useEffect} from 'react'
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import getJwt from "../../helpers/getJwtClient";

type exchangeProps = {
    requiredCard: number;
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


  export default function UserCounteroffers({requiredCard}: exchangeProps) {
    const EMPTY_CARD_IMG_LOC = '/static/images/emptycard.png'
    const CARD_PICTURE_LOC = '/static/images/cards/'
    const { user, isLoading } = useUser();
    const [exchangeRequest, setExchangeRequest] = useState(true);
    const [exchangedContent, setExchangedContent] = useState<exchangeRequestD[]>([]);
    const [cardContent, setCardContent] = useState<barajita>();
    const [cardId, setCardId] = useState(true);
    const API_EXCHANGE_REQUEST_URL = process.env.NEXT_PUBLIC_EXCHANGE_COUNTEROFFER_URL + `/receiver/2`;

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
        )
        const data = await response.json();
        //console.log(data)
        setExchangedContent(data);
        setExchangeRequest(false);
      };
      getExchangeRequestData();
    },[])
    

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
    const cardRequested = cardContent ? cardContent.name : 'Kylian Mbappé';
    if(isLoading || exchangeRequest) return <div>Loading...</div>;
    return(
          <div>
            {exchangedContent.length>0 ?
            exchangedContent.map((exchange, index) => ( 
                <div key={index}>
                  <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                  <div className="p-4">
                  <div className="flex justify-start items-center">   
                  <h1 className="text-1xl font-bold space-y-4"> {`Contraoferta de la barajita en la oferta tal `} </h1> 
                    <span>
                    <Image src={CARD_PICTURE_LOC + cardRequested + ".jpeg" } alt={cardRequested} className="w-20 ml-2 mr-2" width={1080} height={1080} />
                    </span>
                    </div>     
                  </div>
              <div className="flex justify-center space-x-4">
              <Button onClick={accept} text = "Aceptar" color = "green"/> 
            <Button onClick={reject}  text = "Rechazar" color = "red"/>
            <Button onClick={clickMe} text = {`Ver album del solicitante`}/>
              </div>
              </div>
              </div>
            )):
            <div>
            <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                <div className="p-4">
                <div className="flex justify-start items-center">   
                <h1 className="text-1xl font-bold space-y-4"> {`No ha recibido ninguna contraoferta`} </h1> 
                </div>     
                </div>
            </div>
            </div>
  }
            </div>
    )
  }