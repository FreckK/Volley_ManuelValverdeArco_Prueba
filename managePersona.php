<?php

$dbsettings=array(
    'driver'=>'mysql',
    'host'=>'localhost',
    'dbname'=>'GuitarWars',
    'username'=>'root',
    'password'=>'cacaculopedopis',
);
$dbc=new dbconnection($dbsettings);
$con=$dbc->getCon();

//INSERTAR 
//PERSONA COMPLETA
//DEVUELVE BOOLEANO estaInsertado
if(isset($_POST['nombre'])){

    $sql="insert into persona (nombre, DNI, edad, altura) values (:nombre, :dni, :edad, :altura);";
    
    $stmt=$con->prepare($sql);
    
    $stmt->bindParam(':nombre', $_POST['nombre']);
    $stmt->bindParam(':DNI', $_POST['DNI']);
    $stmt->bindParam(':edad', $_POST['edad']);
    $stmt->bindParam(':altura', $_POST['altura']);
    
    echo json_encode(array("estaInsertado" => $stmt->execute()));
}

//SELECT 
//ID
//DEVUELVE PERSONA
if(isset($_GET['id'])){

    $sql="select * from persona where id = :id;";
    
    $stmt=$con->prepare($sql);
    
    $stmt->bindParam(':id', $_GET['id']);
    $stmt->execute();

    $row=$stmt->fetch(PDO::FETCH_ASSOC);

    $json = array();
    if($stmt->rowCount() != 1 || $row['id'] < 0){
        $json['id'] = $row["id"];
        $json['nombre'] = $row["nombre"];
        $json['DNI'] = $row["DNI"];
        $json['edad'] = $row["edad"];
        $json['altura'] = $row["altura"];

    }
    echo json_encode($json);
}

//UPDATE
//ID Y PERSONA
//DEVUELVE estaActualizado
if(isset($_PUT['id'])){

    $sql="delete from persona where id = :id";
    
    $stmt=$con->prepare($sql);
    
    $stmt->bindParam(':id', $_PUT['id']);
    $stmt->execute();
    
    $sql="insert into persona (id, nombre, DNI, edad, altura) values (:id, :nombre, :dni, :edad, :altura);";
    
    $stmt=$con->prepare($sql);
    
    $stmt->bindParam(':id', $_PUT['id']);
    $stmt->bindParam(':nombre', $_PUT['nombre']);
    $stmt->bindParam(':DNI', $_PUT['DNI']);
    $stmt->bindParam(':edad', $_PUT['edad']);
    $stmt->bindParam(':altura', $_PUT['altura']);
    
    echo json_encode(array("estaActualizado" => $stmt->execute()));
}

//DELETE
//ID
//estaEliminado
if(isset($_DELETE['id'])){

    $sql="delete from persona where id = :id";
    
    $stmt=$con->prepare($sql);
    
    $stmt->bindParam(':id', $_DELETE['id']);
    echo json_encode(array("estaEliminado" => $stmt->execute()));
}

class DBConnection{

    protected $settings;
    
    public $dbc;
    
    public function __construct(array $settings){
        $this->settings=$settings;
        
        $this->getDBConnection();
        
    }

    public function getDBConnection(){
        
        //siempre que no este creada antes
        if(isset($this->dbc)==NULL){
        
            $dsn=$this->settings['driver'].':'.
            'host='.$this->settings['host'].';'.
            'dbname='.$this->settings['dbname'].';'.
            'charset=utf8mb4';
            
            $options=array(
                PDO::ATTR_PERSISTENT    => true,
                PDO::ATTR_ERRMODE       => PDO::ERRMODE_EXCEPTION,
            );
            
            try{
                
                $this->dbc=new PDO($dsn,$this->settings['username'],$this->settings['password'],$options);

                
            }catch (PDOException $e){
                echo __LINE__. $e->getMessage();
            }
        }
        
    }
    
    public function getQuery($sql){
        //tipo select
        try{
            $resultset=$this->dbc->query($sql);
            
        }catch(PDOException $ex){
            echo __LINE__.$ex->getMessage();
        }
        return $resultset;
    }

    public function runQuery($sql){
        $affected=0;
        //tipo delete, insert
        try{
            $affected=$this->dbc->exec($sql);
            
        }catch(PDOException $ex){
            echo __LINE__.$ex->getMessage();
        }
        return $affected;
    }

}
