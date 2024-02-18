'use client';
import Badge from "@/app/components/Badge";
import { useUser } from "@auth0/nextjs-auth0/client";

const dummy_leaderboard = [
  {
      id: 1,
      first_name: "John",
      last_name: "Doe",
      email: "",
      birthday: "1990-01-01",
      username: "johndoe",
      cards: 100
  },
  {
      id: 1,
      first_name: "Pepe",
      last_name: "Ganga",
      email: "",
      birthday: "1990-01-01",
      username: "pepeganga",
      cards: 80
  },
  {
      id: 1,
      first_name: "Luisa",
      last_name: "Cáceres",
      email: "",
      birthday: "1990-01-01",
      username: "arismendi",
      cards: 90
  }
]

const total_cards = 100;

export default function Leaderboard() {
  const { user, isLoading } = useUser();
  if (isLoading) return <div>Loading...</div>;

  return (
  <div className="w-full h-full rounded-lg bg-[#d6dfea] p-2 drop-shadow-md">
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4">Leaderboard</h1>
      <div className="flex justify-center">
        <div className="space-y-4 w-1/2">
          {dummy_leaderboard.map((user, index) => (
            <div key={index} className="p-4 rounded-lg bg-white drop-shadow-md flex justify-between px-4">
              <p className="text-lg">@{user.username}</p>
              <div>
                { user.cards == total_cards ? (<Badge text="¡Album lleno!"/>) : null }
                <p className="text-lg font-bold">{user.cards} barajitas</p>
              </div>
            </div>
          ))}
      </div>
      </div>
    </div>
  </div>
  );
}
