
<h1>Fórum hub API</h1>

<strong>Esse projeto foi criado apenas para fins educativos</strong>

<p>Primeiramente você deverá configurar duas variáveis de ambientes no seu SO para conseguir se comunicar com o banco de dados. Elas são:</p>
<p>{<br>
	<strong>MYSQL_USER -> deverá conter o usuário do banco de dados<br>
	MYSQL_PASS -> deverá conter a senha do usuário do banco de dados</strong><br>
}</p>

<h2>Objetivo:</h2>

<h3>*** /signup ***</h3>

<p>Aqui você pode criar o seu usuário, para isso basta passar um json contendo seu nome, email e senha</p>
<p>{<br>
"name": "nickname",<br>
"email": "my.email@email.com",<br>
"password": "p4ssw0rd<br>
}</p>

<p>Após a criação do seu usuário você poderá logar no sistema e tirar suas dúvidas no fórum</p>

<h3>*** /login ***</h3>

<p>Caso não tenha criado seu próprio usuário poderá utilizar o user de teste da própia API. Por padrão api criará um usuário para testes:</p>

<p>name: 'user',<br>
email: 'user@forum.com'<br>
password: '1234'</p>

<p>para logar no forum basta passar o seguinte json:</p>

<p>{<br>
  "email": "seu.email_cadastrado.@email.com",<br>
  "password": "suaSenha"<br>
}</p>

<p>Você precisará estar logado para conseguir fazer as operações disponibilizadas na api(criar, ler, atualizar, deletar, detalhar).
Após se logar, você receberá de resposta um token que será sua chave de acesso a essas operações, você precisará usá-la sempre que for fazer requisições a api.</p>

<h3>*** /topic ***</h3>
[1]  Para criar um tópico basta passar um json para a API utilizando o método POST com os seguintes atributos: title, message, course

<p>Exemplo:

{<br>
	"title" : "Devo aprender C ou C++?",<br>
	"message": "Qual devo escolher?",<br>
	"course": "Linguagem de programacao"<br>
}</p>

[2] - Para listar os tópicos existentes, utilize o método GET. Você pode também manipular quantos tópicos serão exibidos por página bem como em que ordem

[3] - A atualização de um tópico se dá por adicionar o id do tópico que você quer modificar ao json.
<p>{<br>
	"id": "3",<br>
	"message": "Estou na dúvida sobre qual linguagem de programação iniciar meus estudos, pesquisando um pouco na internet achei interessante C, dai vi que também existe C++. Qual começar?",<br>
}</p>

<p>Com isso o tópico que foi salvo no banco de dados com "id: 3" deverá ter a sua mensagem alterada. O que você não quiser modificar basta não acrescentá-la ao json.</p>

PS: Apenas o **título** e **mensagem** são permitidos modificar após a criação do tópico

[4] - A deleção de um tópico se dará por id baseado no autor do mesmo. Ou seja, apenas o criador do tópico é quem poderá excluí-lo, com exceção dos administradores e moderadores.
Exemplo:
<p>
{<br>
"id": "4"<br>
}
O tópico com "id: 4" só será deletado se eu for o autor.
</p>
