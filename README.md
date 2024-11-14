Spring boot application to generate tracking number for given parameter.
■ origin_country_id: The order’s origin country code in ISO 3166-1
alpha-2 format (e.g., "MY").
■ destination_country_id: The order’s destination country code in
ISO 3166-1 alpha-2 format (e.g., "ID").
■ weight: The order’s weight in kilograms, up to three decimal places
(e.g., "1.234").
■ created_at: The order’s creation timestamp in RFC 3339 format (e.g.,
"2018-11-20T19:29:32+08:00").
■ customer_id: The customer’s UUID (e.g.,
"de619854-b59b-425e-9db4-943979e1bd49").
■ customer_name: The customer’s name (e.g., "RedBox Logistics").
■ customer_slug: The customer’s name in slug-case/kebab-case (e.g.,
"redbox-logistics").
Response:
■ tracking_number: The generated tracking number.
■ created_at: The timestamp when the tracking number was generated
(in RFC 3339 format).