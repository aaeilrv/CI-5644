//Exchange requests pending for response
//The ones the user made

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


function deleteRequest() {
    alert("Request deleted!");
  }

  type Barajita = {
    id: number,
    playerName: string,
    playerPosition: string,
    playerNumber: number,
    numberOwned: number,
    country: string,
    photoURL: string,
  }

  type exchangeRequest = {
    id: number,
    user: string,
    requestedCard: Barajita,
    requestStatus: 'PENDING' | 'ACCEPTED' | 'REJECTED',
  }


  export default function UserPendingExchanges({requiredCard}: exchangeProps) {
    const EMPTY_CARD_IMG_LOC = '/static/images/emptycard.png'
    const CARD_PICTURE_LOC = 'static/images/cards/'
    const { user, isLoading } = useUser();
    const [exchangeRequest, setExchangeRequest] = useState(true);
    const [exchangedContent, setExchangedContent] = useState<exchangeRequest[]>([]);
    const API_EXCHANGE_REQUEST_URL = process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL;
    /*
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
        setExchangedContent(data.content);
        setExchangeRequest(false);
      };
      getExchangeRequestData();
    })

    if(isLoading || exchangeRequest) return <div>Loading...</div>;
    */

    return(
      
        <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
         <div className="p-4">
            <div className="flex justify-start items-center">   
            <h1 className="text-1xl font-bold space-y-4"> {`Solicitas intercambiar tu barajita`} </h1> 
            {/** 
            {exchangedContent.map((exchange, index) => (
                <div key={index}>
                    <span>
                        <Image src={ CARD_PICTURE_LOC + exchange.requestedCard.playerName + '.jpeg'} alt={exchange.requestedCard.playerName} className="w-20 ml-2 mr-2" width={1080} height={1080} />
                    </span>
                </div>
            ))}
            **/}
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