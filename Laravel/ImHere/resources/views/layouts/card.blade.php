@extends('layouts.app')

@section('content')
<div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    @yield('card-title')
                </div>
                <div class="card-body">
                    @yield("card-body")
                </div>
            </div>
            <div class="links">
                <a href="/students">Students</a> 
                <a href="/courses">Courses</a> 
                <a href="/seances">Seances</a> 
                <a href="/presences">Presences</a> 
            </div> 
        </div>
</div>  
    
@endsection