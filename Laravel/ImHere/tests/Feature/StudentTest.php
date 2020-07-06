<?php

namespace Tests\Feature;

use Tests\TestCase;
use App\Student;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\DatabaseMigrations;

class StudentTest extends TestCase
{

    use DatabaseMigrations;
    /**
     * A basic feature test example.
     *
     * @return void
     */
    public function testExample()
    {

        $user = new Student([
            "studentId"=>'1000',
            "name"=>"selim",
            "surname"=>"jebira"
        ]);
        $user->save();
        $response = $this->get('/students');
        $response->assertStatus(200);
        $response->assertSeeText($user->name);
        $user->delete();
        
    }
    public function testEmptyCreate(){
        $response = $this->withHeader("AddStudent","Test")->json("POST","/students",['name'=>'','surname'=> "","studentId"=>""]);
        $response
        ->assertSeeText("The name field is required")
        ->assertSeeText("The surname field is required")
        ->assertSeeText("The student id field is required");

    }

    public function testEmptyCreateName(){
        $response = $this->withHeader("AddStudent","Test")->json("POST","/students",['name'=>'','surname'=> "TEST","studentId"=>"1000"]);
        $response
        ->assertSeeText("The name field is required");

    }
    public function testEmptyCreatesurName(){
        $response = $this->withHeader("AddStudent","Test")->json("POST","/students",['name'=>"TEST",'surname'=> "","studentId"=>"1000"]);
        $response
        ->assertSeeText("The surname field is required");

    }
    public function testEmptyCreateStudentId(){
        $response = $this->withHeader("AddStudent","Test")->json("POST","/students",['name'=>"TEST",'surname'=> "TEST","studentId"=>""]);
        $response
        ->assertSeeText("The student id field is required");

    }
    public function testAddCorrectly(){
        $response = $this->withHeader("AddStudent","Test")->json("POST","/students",['name'=>"TEST",'surname'=> "TEST","studentId"=>"1000"]);
        $response->assertSeeText("Redirecting");
    }

    public function testRemoveCorrectly(){
        $this->withHeader("addStudent","Test")->json("POST","students",["name"=>"TEST","surname"=>"TEST","studentId"=>"1000"]);
        $response = $this->withHeader("removeStudent","Test")->json("DELETE","/students/1");
        $response->assertSeeText("Redirecting");
    }

    public function testBadIdAlreadyIn(){
        $response = $this->withHeader("addStudent","Test")->json("POST","students",["name"=>"TEST","surname"=>"TEST","studentId"=>"1000"]);
        $response = $this->withHeader("addStudent","Test")->json("POST","students",["name"=>"TEST","surname"=>"TEST","studentId"=>"1000"]);
        $response->assertSeeText("already been taken");
    }

    public function testBadIdNegative(){
        $response = $this->withHeader("addStudent","Test")->json("POST","students",["name"=>"TEST","surname"=>"TEST","studentId"=>"-4"]);
        $response->assertSeeText("must be at least 0");
    }
    public function testBadIdZero(){
        $response = $this->withHeader("addStudent","Test")->json("POST","students",["name"=>"TEST","surname"=>"TEST","studentId"=>"0"]);
        $response->assertSeeText("student id is invalid");
    }

}
