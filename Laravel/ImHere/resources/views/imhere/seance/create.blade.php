@extends('layouts.card')
@section('card-title')
  @if (isset($seances))
      Edit Seance
  @else
      Add Seance
  @endif
@endsection
@section('card-body')

    @if (isset($seance))
    <form action="/seances/{{$seance->id}}" method="POST">
        @method("PUT")
        <div class="form-group">
            @csrf
            <label for="slug">Slug</label>
            <select name="slug" id="slug">
                <option value={{$seance->name}}>{{$seance->name}}</option>
                    @foreach ($courses as $course)
                        <option value="{{$course->slug}}">{{$course->slug}}</option>
                    @endforeach
            </select>
            <label for="seanceNb">Seance Number</label>
            <input type="number" class="form-group" name="seanceNb" value={{$seance->seanceNb}}>
            <label for="local">Local</label>
            <input type="text" class="form-group" name="local" value={{$seance->local}}>
            <label for="date">Date</label>
            <input type="date" class="form-group" name="date" value={{$seance->date}}>
            <label for="hour">Hour</label>
            <input type="time" class="form-group" name="hour" value ={{$seance->hour}}>
            <label for="duration">Duration</label>
            <input type="time" class="form-group" name="duration" value={{$seance->duration}}>
            
        </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
        
    @else
    <form action="/seances" method="POST">

        <div class="form-group">
            @csrf
            <label for="slug">Slug</label>
            <select name="slug" id="slug">
                <option value="">Select an option</option>
                    @foreach ($courses as $course)
                        <option value="{{$course->slug}}">{{$course->slug}}</option>
                    @endforeach
            </select>
            <label for="seanceNb">Seance Number</label>
            <input type="number" class="form-group" name="seanceNb">
            <label for="local">Local</label>
            <input type="text" class="form-group" name="local">
            <label for="date">Date</label>
            <input type="date" class="form-group" name="date">
            <label for="hour">Hour</label>
            <input type="time" class="form-group" name="hour">
            <label for="duration">Duration</label>
            <input type="time" class="form-group" name="duration">
        </div>
    <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    @endif
    
@endsection