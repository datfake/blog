import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { Cate } from '../models/cate';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CateService {

  constructor(private apiService: ApiService) {}

  public GetPosts(): Observable<Cate[]> {
    return this.apiService.Get(environment.api.entries+"/categories").pipe(
      map(json => {
        return json.map(post => new Cate(post));
      })
    );
  }

  public GetPost(id: string): Observable<Cate> {
    return this.apiService.Get(environment.api.entries + "/categories/" + id).pipe(
      map(json => {
        return new Cate(json);
      })
    );
  }

  public CreatePost(post: Cate): Observable<any> {
    return this.apiService.Post(environment.api.entries + "/categories", post);
  }

  public deletePost(id) {
    return this.apiService.Delete(environment.api.entries + "/categories/" + id);
  }
}
