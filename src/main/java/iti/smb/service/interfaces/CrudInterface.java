package iti.smb.service.interfaces;


import iti.smb.service.model.network.Header;

import java.util.List;

public interface CrudInterface<Request, Response, T> {

    Header<Response> create(Request request);

    Header<List<Response>> list();

    Header<Response> read(T t);

    Header<Response> update(Request request);

    Header delete(Long id);

}
