> Observação: É possível iniciar sem os arquivos de dados (.csv) locais, que ficam dentro de */data*.

# brands

## Buscar

### Todas
* Requisição tipo **get** para **/brands**
* Retorna todas as **brands**

### Por id
* Requisição tipo **get** para **/brands/{id}**
  * Se não existir nenhuma **brand**, com o **id** informado, retorna erro.
* Em caso de sucesso, retorna a **brand** do **id** informado.

## Inserir
* Requisição tipo **post** para **/brands**
* Corpo da requisição precisa conter o atributo **description**.
  * Atributo **id** é opcional
    * Se for omitido, será gerado um automaticamente.
    * Se existir no corpo da requisição
      * Se já existir **brand** com o **id** informado, retorna erro.
      * Se o **id** informado for menor que o maior **id** existente, retorna erro.
  * Se não existir o atributo **description**, retorna erro.
* Em caso de sucesso, retorna a **brand** inserida.

## Remover
* Requisição tipo **delete** para **/brands/{id}**
* Remove a **brand** do **id** informado
  * Se não existir nenhuma **brand**, com o **id** informado, retorna erro.
* Em caso de sucesso, retorna apenas status *200*.

## Alterar atributo único
* Requisição tipo **patch** para **/brands/{id}**
* Corpo da requisição precisa dos atributos
  * **attribName**: nome do atributo a ser alterado
  * **attribValue**: novo valor do atributo
  * Se não existir nenhuma **brand**, com o **id** informado, retorna erro.
  * Se um dos atributos for omitido, retorna erro.
  * Se **attribName** conter um nome de atributo que não existe ou **id**, retorna erro.
* Em caso de sucesso, retorna o objeto com o atributo contendo o valor novo.

## Alterar todos os atributos
* Requisição tipo **put** para **/brands/{id}**
* Corpo da requisição precisa do atributo obrigatório **description** (nova descrição)
  * Se não existir nenhuma **brand**, com o **id** informado, retorna erro.
  * Se o atributo obrigatório for omitido, retorna erro.
* Em caso de sucesso, retorna o objeto com o atributo contendo os valores novos.


# cars

## Buscar

### Todos
* Requisição tipo **get** para **/cars**
* Retorna todos os **cars**

### Por id
* Requisição tipo **get** para **/cars/{id}**
  * Se não existir nenhum **car**, com o **id** informado, retorna erro.
* Em caso de sucesso, retorna o **car** do **id** informado.

## Inserir
* Requisição tipo **post** para **/cars**
* Corpo da requisição precisa conter os atributos obrigatórios: **brandId**, **model** e **color**.
  * **brandId**: id da **brand** a que este **car** se refere.
  * **model**: modelo do **car**
  * **color**: cor do **car**
  * Atributo **id** é opcional
    * Se for omitido, será gerado um automaticamente.
    * Se existir no corpo da requisição
      * Se já existir **car** com o **id** informado, retorna erro.
      * Se o **id** informado for menor que o maior **id** existente, retorna erro.
  * Se não existir qualquer um dos atributos obrigatórios, retorna erro.
  * Se **brandId** for igual ao **id** de uma **brand** que não existe, retorna erro.
* Em caso de sucesso, retorna o **car** inserida.

## Remover
* Requisição tipo **delete** para **/cars/{id}**
* Remove o **car** do **id** informado
  * Se não existir nenhum **car**, com o **id** informado, retorna erro.
* Em caso de sucesso, retorna apenas status *200*.

## Alterar atributo único
* Requisição tipo **patch** para **/cars/{id}**
* Corpo da requisição precisa dos atributos obrigatórios: **attribName** e **attribValue**.
  * **attribName**: nome do atributo a ser alterado
  * **attribValue**: novo valor do atributo
  * Se não existir nenhum **car**, com o **id** informado, retorna erro.
  * Se um dos atributos for omitido, retorna erro.
  * Se **attribName** conter um nome de atributo que não existe ou **id**, retorna erro.
  * Se **attribName** for **brandId** e **attribValue** for igual ao **id** de uma **brand** que não existe, retorna erro.
* Em caso de sucesso, retorna o objeto com o atributo contendo o valor novo.

## Alterar todos os atributos
* Requisição tipo **put** para **/cars/{id}**
* Corpo da requisição precisa conter os atributos obrigatórios: **brandId**, **model** e **color**.
  * Se não existir nenhum **car**, com o **id** informado, retorna erro.
  * Se qualquer um dos atributos obrigatórios for omitido, retorna erro.
  * Se **brandId** for igual ao **id** de uma **brand** que não existe, retorna erro.
* Em caso de sucesso, retorna o objeto com o atributo contendo os valores novos.
