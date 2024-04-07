//Exchange view

"use client";
import { useUser } from "@auth0/nextjs-auth0/client";
import ExchangeNotifications from "../../components/exchange/offers/exchangeOffersReceived";
import ExchangeNotificationsMade from "../../components/exchange/offers/exchangeOffersMade";
import ExchangeRequest from "../../components/exchange/requests/exchangeRequest";
import ExchangeRequestsFromOtherUsers from "../../components/exchange/requests/exchangeRequestsFromOtherUsers";
import UserPendingExchanges from "../../components/exchange/requests/userPendingExchanges";
import UserCounteroffers from "../../components/exchange/counterOffers/counterOfferReceived";
import UserCounteroffersMade from "../../components/exchange/counterOffers/counterOfferSended";
import OffersSidebar from "@/app/components/exchange/offersSidebar/offersSidebar";

export default function Exchange() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (
    <div>
      <OffersSidebar />
      {/*<div>
        <ExchangeRequest />
      </div>*/}
      {/*<div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4 mt-4">
            {" "}
            Mis solicitudes de intercambio{" "}
          </h1>
        </div>
        <div className="p-4">
          <UserPendingExchanges />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Ofertas de intercambio pendientes{" "}
          </h1>
        </div>
        <div className="p-4">
          <ExchangeNotifications />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Ofertas de intercambio hechas{" "}
          </h1>
        </div>
        <div className="p-4">
          <ExchangeNotificationsMade />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Peticiones de otros usuarios{" "}
          </h1>
        </div>
        <div className="p-4">
          <ExchangeResquestFromOtherUsers />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Contraofertas recibidas{" "}
          </h1>
        </div>
        <div className="p-4">
          <UserCounteroffers />
        </div>
      </div>
      <div>
        <div className="p-4">
          <h1 className="text-2xl font-bold space-y-4">
            {" "}
            Contraofertas enviadas{" "}
          </h1>
        </div>
        <div className="p-4">
          <UserCounteroffersMade />
        </div>
    </div>*/}
    </div>
  );
}
