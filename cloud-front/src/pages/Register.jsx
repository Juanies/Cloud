import React, { useState } from 'react';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [mail, setMail] = useState('');
  const [phone, setPhone] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);
    formData.append('mail', mail);
    formData.append('phone', phone);

    try {
      const response = await fetch('http://localhost:8080/api/user/register', {
        method: 'POST',
        body: formData,
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      console.log('Response:', data);
      // Puedes manejar la respuesta aquí según tus necesidades

      // Limpiar los campos del formulario si es necesario
      setUsername('');
      setPassword('');
      setMail('');
      setPhone('');
    } catch (error) {
      console.error('Error:', error.message);
      // Puedes manejar el error aquí según tus necesidades
    }
  };

  return (
    <div className='container'>
      <form onSubmit={handleSubmit}>
        <label>Username:</label>
        <input type='text' value={username} onChange={(e) => setUsername(e.target.value)} />
        
        <label>Password:</label>
        <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
        
        <label>Mail:</label>
        <input type='text' value={mail} onChange={(e) => setMail(e.target.value)} />
        
        <label>Phone:</label>
        <input type='text' value={phone} onChange={(e) => setPhone(e.target.value)} />
        
        <button type='submit'>Register</button>
      </form>
    </div>
  );
}

export default Register;
