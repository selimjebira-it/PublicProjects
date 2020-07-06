<?php

use Illuminate\Database\Seeder;

class SeanceSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $faker = Faker\Factory::create("fr_FR");
        for($i = 0 ; $i < 10 ; $i ++){

            $seance = new Seance;
            
        }
    }
}
