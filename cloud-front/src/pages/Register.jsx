import { useState } from 'react';
import Input from '../components/Input';
import Title from '../components/Title';

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
        <Title text='Welcome'/>
      <form onSubmit={handleSubmit}>
        <input type='text' value={username} onChange={(e) => setUsername(e.target.value)} />
        <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
        <input type='text' value={mail} onChange={(e) => setMail(e.target.value)} />
        <Input type='text' text='Phone' name='phone' value={phone} onChange={(e) => setPhone(e.target.value)} />
        <button type='submit'>Register</button>
      </form>
    </div>
  );
}

export default Register;
