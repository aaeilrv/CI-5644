"use client";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useUser } from "@auth0/nextjs-auth0/client";
import CreatePurchase from "@/app/components/purchase/createPurchase";
import Button from "@/app/components/Button";

const BASE_URL = "/api/v1/purchase";

function PurchaseView() {
  const { user } = useUser();
  const [quantity, setQuantity] = useState(1);
  const pricePerPacket = 5; // base price
  const [totalPrice, setTotalPrice] = useState(pricePerPacket); // Total price

  // Updates price when the quantity increases
  const handleIncrement = () => {
    setQuantity(quantity + 1);
    setTotalPrice((quantity + 1) * pricePerPacket);
  };

  // Updates price when the quantity decreases
  const handleDecrement = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
      setTotalPrice((quantity - 1) * pricePerPacket);
    }
  };

  return (
    <div className="flex w-full h-full rounded-lg bg-gray-200 p-4 drop-shadow-md">
      <div className="flex flex-row justify-center items-center p-8">
        <div className="w-96">
          <img
            src="https://lh3.googleusercontent.com/93W45_EJoZpKjy4395cKPQ2F7LqkDmMcZLKFbxeYXQVH2oZ74mcHI9Z2zJZ6Gl5pZgYlqfPNWLTBKY-cog0RHSRmty7g8UzsgusJUIQitN_sVIM"
            alt="Paquete de 5 barajitas"
            width={1000}
            height={1000}
            loading="lazy"
            className="bg-white p-4 rounded-lg"
          />
        </div>
        <div className="flex flex-col ml-8 text-lg">
          <h1 className="mb-4 font-extrabold text-3xl">
            Paquete de barajitas aleatorias
          </h1>
          <p className="mb-3 font-bold text-lg">¡Vive la pasión del fútbol!</p>
          <p className="text-base w-1/2 mb-8">
            Cada paquete incluye 5 barajitas. Encuentra cartas especiales y
            comienza tu colección.
          </p>

          <div className="flex flex-row items-center mb-8">
            <p className="mr-8 font-semibold">Cantidad:</p>
            <button
              onClick={handleDecrement}
              className="text-black py-1 rounded"
            >
              -
            </button>
            <input
              type="number"
              value={quantity}
              min={1}
              readOnly
              className="w-32 text-center text-lg border-gray-200 rounded-lg mx-2"
            />
            <button
              onClick={handleIncrement}
              className="text-black py-1 rounded"
            >
              +
            </button>
            <span className="ml-8 font-semibold">Precio: ${totalPrice}</span>
          </div>
          <Button
            onClick={() => {
              CreatePurchase(quantity);
            }}
            text="Comprar paquete"
          />
        </div>
      </div>
    </div>
  );
}

export default PurchaseView;
