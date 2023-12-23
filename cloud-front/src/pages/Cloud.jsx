import React, { useState, useRef } from 'react';
import './Cloud.css';
import image from './../assets/Logo.png';
import user from '../assets/user.jpeg';
import Favorite from '../assets/star-regular.svg';


function Cloud() {
  const asideRef = useRef(null);
  const asidebarRef = useRef(null);
  const [isDragging, setIsDragging] = useState(false);
  const [initialCursorX, setInitialCursorX] = useState(0);

  const handleMouseDown = (e) => {
    if (e.target === asidebarRef.current) {
      setIsDragging(true);
      setInitialCursorX(e.clientX);
    }
  };




  const handleMouseUp = () => {
    setIsDragging(false);
  };

  const handleMouseMove = (e) => {
    if (isDragging) {
      const { clientX } = e;

      const movementX = clientX - initialCursorX;

        setAsideWidth(asideWidth + movementX);
        setInitialCursorX(clientX); 
    }
  };

    const [dropdown1, setDropdown1] = useState(true);
    const [dropdown2, setDropdown2] = useState(true);

  const desplegable = (contenidoId) => {
      if (contenidoId === 'contenido1') {
          setDropdown1((prevDropdown) => !prevDropdown);
      } else if (contenidoId === 'contenido2') {
          setDropdown2((prevDropdown) => !prevDropdown);
      }
    }
    const [filesData, setFilesData] = useState([
      { id: 1, urlimg: 'image1.jpg', urlimg2: 'image1.jpg', namefile: 'file1.txt', datefile: '11/11/2005' },
      { id: 2, urlimg: 'image2.jpg', urlimg2: 'image1.jpg', namefile: 'file2.txt', datefile: '11/11/2005' },
      { id: 3, urlimg: 'image3.jpg', urlimg2: 'image1.jpg', namefile: 'file3.txt', datefile: '11/11/2005' },
      // Add more objects as needed
    ]);

    const [isFavorite, setFavorite] = useState([
      
    ]);


    const handleClick = (clickedId) => {
      const updatedFilesData = [...filesData];
      const clickedFileIndex = updatedFilesData.findIndex((file) => file.id === clickedId);
      const clickedFile = updatedFilesData.splice(clickedFileIndex, 1)[0];
      updatedFilesData.unshift(clickedFile);
      setFilesData(updatedFilesData);
      isFavorite.push(clickedFile);
      console.log('Updated Order:', isFavorite.map((file) => file.namefile));
    };

    const removeFavorite = (clickedId) => {
      setFavorite((prevFilesData) => {
        const updatedFilesData = prevFilesData.filter((file) => file.id !== clickedId);

        console.log('Updated Order:', updatedFilesData.map((file) => file.namefile));
    
        return updatedFilesData;
      });
    };

  const [asideWidth, setAsideWidth] = useState(300); // Ancho inicial del aside

  return (
    <div className='cloud-container'>
      <header className='cloud-header'>
        <ul className='cloud-ul'>
          <li className='cloud-li container-img'>
            <img src={image} alt="image1" />
          </li>
          <li className='cloud-li'>
            <input type="text" placeholder='search' />
          </li>
          <li className='cloud-li container-img'>
            <img src={user} className='image2' alt="image2" />
          </li>
        </ul>
      </header>
        <div className='main-container'>
          <aside
            id="aside"
            className='sidebar'
            style={{ width: asideWidth + 100 + 'px', overflowX: 'auto' }}
            onMouseDown={handleMouseDown}
            onMouseUp={handleMouseUp}
            onMouseMove={handleMouseMove}
            ref={asideRef}
          >
            <div className='asidebar' ref={asidebarRef}> </div>
            {filesData.map((file, index) => (
              <div key={index} className='file'>
                <img src={file.urlimg} alt="" />
                <p className='namefile'> {file.namefile} </p>
                <p className='datefile'>{file.datefile}</p>
                <img onClick={() => handleClick(file.id)} className='star' src={Favorite} alt="" />
              </div>
          ))}
          </aside>
          <section>
            <div className='section-special'>
                <h3 onClick={() => desplegable('contenido1')}>Favoritos</h3>
                <div id='contenido1' className={`dropdown ${dropdown1 ? '' : 'dropdown-hidden'}`}>
                {isFavorite.map((file, index) => (
              <div key={index} className='file'>
                <img src={file.urlimg} alt="" />
                <p className='namefile'> {file.namefile} </p>
                <p className='datefile'>{file.datefile}</p>
                <img onClick={() => removeFavorite(file.id)} className='star' src={Favorite} alt="" />
              </div>
            ))}
                </div>
            </div>
            <div className='section-special'>
                <h3 onClick={() => desplegable('contenido2')}>Reciente</h3>
                <div id='contenido2' className={`dropdown ${dropdown2 ? '' : 'dropdown-hidden'}`}>
                {filesData.map((file, index) => (
            <div key={index} className='file'>
              <img src={file.urlimg} alt="" />
              <p className='namefile'> {file.namefile} </p>
              <p className='datefile'>{file.datefile}</p>
              <img src={Favorite} onClick={() => handleClick(file.id)} className='star'  alt="" />
            </div>
          ))}
                </div>
            </div>
        </section>
        </div>
    </div>
  );
}

export default Cloud;
