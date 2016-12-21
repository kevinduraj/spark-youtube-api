-- # broadcast_address
-- # rpc_address: 35.164.75.145
-- listen_address: 172.31.1.23
-- broadcast_rpc_address: 35.164.75.145

--- If rpc_broadcast_address is not set, rpc_address will be used. 
--- Set one of these addresses to an address that will be accessible by your client. 
--- If you set rpc_address, you will want to remove broadcast_rpc_address.

--- show original peer
select peer, data_center, host_id, rpc_address from system.peers;

--- update remote peer
update system.peers set rpc_address = '35.164.75.145' where peer = '35.164.75.145';
select peer, data_center, host_id, rpc_address from system.peers;

