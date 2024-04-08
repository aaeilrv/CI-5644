"use client";

import getJwt from "../../helpers/getJwtClient";

export default function CreatePurchase(numberOfPackets: number) {
  const API_PURCHASE_POST_URL = process.env.NEXT_PUBLIC_PURCHASE_URL + ``;

  const postRequest = async () => {
    const { token } = await getJwt();
    const response = await fetch(API_PURCHASE_POST_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({
        amount: numberOfPackets,
        creditCardId: 1,
        createdAt: new Date().toISOString(),
      }),
    })
      .then((response) => response.json())
      .then((data) => console.log(data))
      .catch((error) => {
        console.error("Error:", error);
      });
  };
  postRequest();
  alert("Compra realizada!");
}
