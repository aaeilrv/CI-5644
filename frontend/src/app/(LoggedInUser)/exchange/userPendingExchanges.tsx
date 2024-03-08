//Exchange requests pending for response
//The ones the user made

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import Image from "next/image";
import Button from "@/app/components/Button";
import { Fragment, useState , useEffect} from 'react'
import { barajitas_temporal } from "@/utils/barajitas_temporal";
import getJwt from "../../helpers/getJwtClient";
import getCardName from "./getCardName";
import UpdateExchangeRequest from "./updateExchangeRequest";

type exchangeProps = {
    requiredCard: number;
  };


function deleteRequest() {
    alert("Request deleted!");
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
    status:string,
  }


  export default function UserPendingExchanges({requiredCard}: exchangeProps) {
    const CARD_PICTURE_LOC = '/static/images/cards/'
    const { user, isLoading } = useUser();
    const [exchangeRequest, setExchangeRequest] = useState(true);
    const [exchangedContent, setExchangedContent] = useState<exchangeRequestD[]>([]);
    const [cardContent, setCardContent] = useState<barajita>();
    const [cardId, setCardId] = useState(true);
    const API_EXCHANGE_REQUEST_URL = process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL + `/user/3`;
    const API_EXCHANGE_REQUEST_PATCH_URL = process.env.NEXT_PUBLIC_EXCHANGE_REQUEST_URL + ``;

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
     // console.log(data);
      setCardContent(data);
      setCardId(false);
    };
    getCardData();
  } ,[])
    const cardRequested = cardContent ? cardContent.name : 'Kylian Mbappé'; 
    /** 
    useEffect(() => {
      const fetchCardNames = async () => {
        const names = await Promise.all(exchangedContent.map(exchange => getCardName(exchange.requestedCardId)));
        setCardContent(names);
      };
    
      fetchCardNames();
    }, [exchangedContent]);
    console.log(cardContent);
    */
   
    if(isLoading || exchangeRequest) return <div>Loading...</div>;
    return(
          <div>
            {exchangedContent.length>0 ?
            exchangedContent.map((exchange, index) => ( 
                <div key={index}>
                  <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                  <div className="p-4">
                  <div className="flex justify-start items-center">   
                  <h1 className="text-1xl font-bold space-y-4"> {`Solicitas intercambiar tu barajita`} </h1> 
                    <span>
                    <Image src={CARD_PICTURE_LOC + cardRequested + ".jpeg" } alt={cardRequested} className="w-20 ml-2 mr-2" width={1080} height={1080} />
                    </span>
                    </div>
                    <div className="flex justify-center space-x-4">
              <h1 className="text-1xl font-bold space-y-4"> {`Estatus de la solicitud: ${exchange.status}`} </h1> 
              </div>       
                  </div>
              <div className="flex justify-center space-x-4">
                <Button onClick={()=>UpdateExchangeRequest(exchange.id,exchange.userId,exchange.requestedCardId,"CANCELLED")} text = {"Borrar solicitud de intercambio"}/>
              </div>
              </div>
              </div>
          )) :
            <div>
                  <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
                  <div className="p-4">
                  <div className="flex justify-start items-center">   
                  <h1 className="text-1xl font-bold space-y-4"> {`No has solicitado ningun intercambio`} </h1> 
                  </div>     
                  </div>
              </div>
              </div>
          }
          </div>

    )
  }