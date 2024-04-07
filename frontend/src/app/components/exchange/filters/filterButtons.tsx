import React, { useState } from 'react';

const FilterButtons = ({currentFilter, setCurrentFilter}: {currentFilter: number, setCurrentFilter: React.Dispatch<React.SetStateAction<number>>}) => {

  const [filters, setFilters] = useState([
    {
      id: 0,
      name: "Solicitudes",
      active: true,
      background: "bg-orange-400",
      ring: "ring-orange-500",
    },
    {
      id: 1,
      name: "Ofertas",
      active: false,
      background: "bg-lime-400",
      ring: "ring-lime-500",
    },
    {
      id: 2,
      name: "Contraofertas",
      active: false,
      background: "bg-sky-400",
      ring: "ring-sky-500",
    }
  ]);

  const handleButtonClick = (filterId: number) => {
    setFilters(prevFilters =>
      prevFilters.map(filter =>
        filter.id === filterId ? { ...filter, active: true } : { ...filter, active: false }
      )
    );
    setCurrentFilter(filterId);
  };

  return (
    <>
      <div className='space-x-4'>
        {
          filters.map((filter) => (
            <button
              key={filter.id}
              className={`inline-flex items-center rounded-md ${filter.active ? filter.background : ''} px-2 py-1 text-xs font-medium text-black ring-1 ring-inset ${filter.ring}`}
              onClick={() =>
                handleButtonClick(filter.id)
              }
            >
              {filter.name}
            </button>
          ))
        }
      </div>
    </>
  );
};

export default FilterButtons;
